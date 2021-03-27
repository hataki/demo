package com.HashMapTry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

public class MyHashMapTest {


    @Test
    public void MyHashMapTest(){


        Map map = new HashMap(64);
        for (int i = 0; i < 100000000; i++) {
            map.put("test","test is ok !");
            System.err.println( map.get("test") + " :" + i );
        }


    }



}
