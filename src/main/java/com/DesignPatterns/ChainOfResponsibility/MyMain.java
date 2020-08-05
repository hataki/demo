package com.DesignPatterns.ChainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/8/5
 * Time: 9:46
 * description:
 */
public class MyMain {

    public static void main(String[] args) {
        Message m = new Message();
        m.setMsg("<(*oo*)>we are 996 !</(*oo*)>");

        /**
         * origin
         */
        new HtmlFilter().doFilter(m);
        new SensitiveFilter().doFilter(m);
        System.out.println(m.getMsg());

        /**
         * level up
         */
        FilterChain filterChain = new FilterChain();
        filterChain.add(new HtmlFilter()).add(new SensitiveFilter());
//        filterChain.add(new SensitiveFilter());
        filterChain.doFilter(m);
        System.out.println(m.getMsg());

        /**
         * add chain
         * and like this
         */
        FilterChain filterChain2 = new FilterChain();
        filterChain2.add(new HtmlFilter()).add(new SensitiveFilter()).add(filterChain);
        filterChain.doFilter(m);
        System.out.println(m.getMsg());

    }
}

class SensitiveFilter implements Filter{

    @Override
    public void doFilter(Message m) {
        String r = m.getMsg();
        r = r.replace("996","965");
        m.setMsg(r);
    }
}


class HtmlFilter implements Filter{

    @Override
    public void doFilter(Message m) {
        String r = m.getMsg();
        r = r.replace('<','[');
        r = r.replace('>',']');
        m.setMsg(r);
    }
}

/**
 * make a responsibility chain
 * FilterChain类实现Filter接口，就可以把它看作一个Filter的实现类，
 * 从而链接到其他FilterChain里面
 */
class FilterChain implements Filter{
    List<Filter> filters = new ArrayList<>();

    /**
     * 技巧改进
     * @param f
     */
    public FilterChain add(Filter f){
        filters.add(f);
        return this ;
    }

    public void doFilter(Message m){
        for(Filter f : filters){
            f.doFilter(m);
        }
    }

}