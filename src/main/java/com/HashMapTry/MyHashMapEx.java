package com.HashMapTry;

import org.junit.Test;

import java.util.HashMap;

public class MyHashMapEx extends HashMap {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;


    @Test
    public void HashMapTry(){

        Integer i = 16 ;



        System.err.println(i.getClass().hashCode()   );
        System.err.println(i.hashCode()   );
        System.err.println(((Integer) DEFAULT_INITIAL_CAPACITY).getClass().hashCode());
        System.err.println(((Integer) DEFAULT_INITIAL_CAPACITY).hashCode());

    }
}
