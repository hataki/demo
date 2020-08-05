package com.DesignPatterns.ChainOfResponsibility;

/**
 * @Author: hataki
 * @Date: 2020/8/5
 * Time: 9:46
 * description:
 */
public class MyFilter implements Filter {
    @Override
    public Boolean doFilter(Message m) {
        String r = m.getMsg();
        r = r.replace('<','[');
        r = r.replace('>',']');
        m.setMsg(r);
        return true ;
    }
}
