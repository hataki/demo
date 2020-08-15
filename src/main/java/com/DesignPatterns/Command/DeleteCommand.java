package com.DesignPatterns.Command;

/**
 * @Author: hataki
 * @Date: 2020/8/15
 * Time: 15:40
 * description:
 */
public class DeleteCommand extends Command{

    Content c ;
    String deleted ;

    public DeleteCommand(Content c ){
        this.c = c ;
    }

    @Override
    public void execute() {
        deleted = c.msg.substring(0,5);
        c.msg = c.msg.substring(5,c.msg.length());
    }

    @Override
    public void undo() {
        c.msg = deleted + c.msg ;
    }
}
