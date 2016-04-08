package com.fcs.demo.collection.hashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/4/6.
 */
public class TestHashMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        int capacity = 1;
        capacity <<= 1;
        System.out.println(capacity);
    }
}
