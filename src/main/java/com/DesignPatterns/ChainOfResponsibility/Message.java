package com.DesignPatterns.ChainOfResponsibility;

/**
 * @Author: hataki
 * @Date: 2020/8/5
 * Time: 9:49
 * description:
 */
public class Message {
    private String msg ;
    private String content ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
