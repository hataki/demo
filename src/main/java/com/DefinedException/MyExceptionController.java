package com.DefinedException;


import lombok.Data;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ControllerAdvice
 *   1. 全局异常处理
 *   2. 全局数据绑定
 *   3. 全局数据预处理
 *
 *  我们通常会把 事务 配置在 Service层，当数据库操作失败时让 Service 层抛出运行时异常，
 *  Spring 事物管理器就会进行回滚。
 *  如此一来，我们的 Controller 层就不得不进行 try-catch Service 层的异常，
 *  否则会返回一些不友好的错误信息到客户端。但是，Controller 层每个方法体都写一些模板化的 try-catch 的代码，
 *  很难看也难维护，特别是还需要对 Service 层的不同异常进行不同处理的时候。
 *
 * 自定义异常处理
 * 需要配合
 */

@ControllerAdvice
public class MyExceptionController {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map MyExceptionHandler(Exception ex ){
        Map map = new HashMap();
        map.put("code",666);
        //判断异常的类型,返回不一样的返回值 , 重写需要重新自定一个的异常信息
        if(ex instanceof MissingServletRequestParameterException){
            map.put("msg","缺少必需参数："+((MissingServletRequestParameterException) ex).getParameterName());
        }
        else if(ex instanceof MyException){
            map.put("msg","这是自定义异常");
        }
       return map ;
    }
}

/**
 *
 */
@Data
class MyException extends RuntimeException {
    private long code;
    private String msg;

    public MyException(Long code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MyException(String msg){
        super(msg);
        this.msg = msg;
    }
}


@RestController
class TestController {
    @RequestMapping("testException")
    public String testException() throws Exception{
        throw new MissingServletRequestParameterException("name","String");
    }

    @RequestMapping("testMyException")
    public String testMyException() throws MyException{
        throw new MyException("i am a myException");
    }
}
