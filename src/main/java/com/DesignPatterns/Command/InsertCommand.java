package com.DesignPatterns.Command;

/**
 * @Author: hataki
 * @Date: 2020/8/15
 * Time: 15:43
 * description:
 */
public class InsertCommand extends Command{
    Content c ;
    String strForInsert = "We are " ;

    public InsertCommand(Content c) {
        this.c = c;
    }


    @Override
    public void execute() {
        c.msg = c.msg + strForInsert;
    }

    @Override
    public void undo() {
        c.msg = c.msg.substring(0,c.msg.length() - strForInsert.length());
    }
}
