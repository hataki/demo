package com;

import org.omg.CORBA.ObjectHelper;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: hataki
 * @Date: 2020/6/19
 * Time: 17:06
 * description:
 */
public class demoJol {
    public static void main(String[] args) throws  Exception{

        /**
         * markword
         * klass pointer
         * instance data
         * padding
         */

        Thread.sleep(5000);

        Object o = new Object() ;
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
