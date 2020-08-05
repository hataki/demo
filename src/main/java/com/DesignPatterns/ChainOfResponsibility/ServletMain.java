package com.DesignPatterns.ChainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/8/5
 * Time: 13:56
 * description:
 */
public class ServletMain {
    public static void main(String[] args) {
        Request request = new Request();
        request.str = "大家不好:)<(*oo*)>we are 996 !</(*oo*)> ";
        Response response = new Response();
        response.str = "response";


        /**
         * add chain
         * and like this
         */
        ServletFilterChain filterChain = new ServletFilterChain();
        filterChain.add(new AppleFilter()).add(new BananaFilter()).add(filterChain);
        filterChain.doFilter(request, response, filterChain);
        System.out.println(request.str);
        System.out.println(response.str);

    }
}

interface ServletFilter {
    boolean doFilter (Request request , Response response , ServletFilterChain chain);
}

/**
 * 要实现Servlet所要求的责任链，需要在过滤器内部做修改
 */
class AppleFilter implements ServletFilter{


    @Override
    public boolean doFilter(Request request, Response response, ServletFilterChain chain) {
        request.str = request.str.replaceAll("<","[").replaceAll(">","]") + "AppleFilter()" ;
        chain.doFilter(request,response,chain);
        response.str += "----AppleFilter()" ;
        return false;
    }
}


class BananaFilter implements ServletFilter{

    @Override
    public boolean doFilter(Request request, Response response, ServletFilterChain chain) {
        request.str = request.str.replaceAll("996","965").replaceAll("不好","真好") + "BananaFilter()" ;
        chain.doFilter(request,response,chain);
        response.str += "----BananaFilter()" ;
        return false;
    }


}

/**
 * make a responsibility chain
 * FilterChain类实现Filter接口，就可以把它看作一个Filter的实现类，
 * 从而链接到其他FilterChain里面
 */
class ServletFilterChain implements ServletFilter{
    List<ServletFilter> filters = new ArrayList<>();
    /**
     * 计数器，用来标记责任链执行到了哪一步
     */
    int index = 0;


    /**
     * 技巧改进
     * @param f
     */
    public ServletFilterChain add(ServletFilter f){
        filters.add(f);
        return this ;
    }



    @Override
    public boolean doFilter(Request request, Response response, ServletFilterChain chain) {
        if(index == filters.size()) return false ;
        ServletFilter f = filters.get(index);
        index ++ ;
        return f.doFilter(request,response,chain) ;
    }
}


class Request {
    String str ;
}
class Response {
     String str ;
}