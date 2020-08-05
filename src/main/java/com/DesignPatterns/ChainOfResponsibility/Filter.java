package com.DesignPatterns.ChainOfResponsibility;

/**
 * @Author: hataki
 * @Date: 2020/8/5
 * Time: 9:48
 * description:
 */
public interface Filter {
    void doFilter(Message m);
}
