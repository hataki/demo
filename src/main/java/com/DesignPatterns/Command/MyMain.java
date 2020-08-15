package com.DesignPatterns.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/8/15
 * Time: 15:45
 * description:
 */
public class MyMain {
    public static void main(String[] args) {
        Content c = new Content() ;

        /**
         * 执行示例
         */
        Command insertCommand = new InsertCommand(c);
            insertCommand.execute();
            insertCommand.undo();
        Command copyCommand = new CopyCommand(c);
            copyCommand.execute();
            copyCommand.undo();
        Command deleteCommand = new DeleteCommand(c);
            deleteCommand.execute();
            deleteCommand.undo();


        /**
         * 怎么实现一些列动作的do 与 undo ？
         * 所有的动作放到一个容器里面，每调用一次execute,执行一下容器里面的command类
         *
         *
         * 创建一个动作链，循环执行，然后再回滚
         */
        List<Command> commands = new ArrayList<>();
        commands.add(new InsertCommand(c));
        commands.add(new CopyCommand(c));
        commands.add(new DeleteCommand(c));

        for(Command comm : commands){
            comm.execute();
        }

        System.out.println(c.msg);

        for(int i=commands.size()-1; i>=0 ; i++){
            commands.get(i).undo();
        }

        System.out.println(c.msg);

    }
}
