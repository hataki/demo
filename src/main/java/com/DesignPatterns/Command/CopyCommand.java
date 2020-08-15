package com.DesignPatterns.Command;

/**
 * @Author: hataki
 * @Date: 2020/8/15
 * Time: 15:37
 * description:
 */
public class CopyCommand extends Command{

    Content c ;

    public CopyCommand(Content c){
        this.c = c ;
    }

    @Override
    public void execute() {
        c.msg = c.msg + c.msg ;
    }

    @Override
    public void undo() {
        c.msg = c.msg.substring(0, c.msg.length()/2);
    }
}
