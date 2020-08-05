package com.DesignPatterns.ChainOfResponsibility;

/**
 * @Author: hataki
 * @Date: 2020/8/5
 * Time: 9:48
 * description:
 *
 * 如果希望责任链执行的过程，由于某一环节而需要结束掉整个责任链时，
 * 那么就需要修改doFilter方法的返回值为Boolean，
 * 以此来添加逻辑控制
 *
 */
public interface Filter {
    Boolean doFilter(Message m);
}
