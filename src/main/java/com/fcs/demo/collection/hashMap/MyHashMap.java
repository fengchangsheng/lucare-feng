package com.fcs.demo.collection.hashMap;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static javafx.scene.input.KeyCode.L;

/**
 * Created by Lucare.Feng on 2016/4/11.
 */
public class MyHashMap<K,V> extends AbstractMap<K,V>{

    private int size = 16;
    private MyEntry<K,V>[] myEntries;

    public MyHashMap(int size) {
        this();
        this.size = size;
    }

    public MyHashMap() {
        myEntries = new MyEntry[size];
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % size;
        if (myEntries[index] == null) {
            return null;
        }
        MyEntry<K,V> myEntry = myEntries[index];
        for (MyEntry<K,V> m = myEntry;m != null; m = m.getNext()) {
            if (m.getKey().equals(key))
                return m.getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % size;
        if (myEntries[index] == null) {
            myEntries[index] = new MyEntry<K, V>(key,value);
        }
        MyEntry myEntry = myEntries[index];
        MyEntry lastEntry = null;
        boolean found = false;
        for (MyEntry<K, V> m = myEntry; m != null; m = m.getNext()) {
            if (m.getKey().equals(key)) {
                oldValue = m.getValue();
                m.setValue(value);
                found = true;
            }
            lastEntry = m;
        }
        if (!found) {
            MyEntry newEntry = new MyEntry(key, value);
            lastEntry.setNext(newEntry);
        }
        return oldValue;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();
        for (MyEntry<K, V> myEntry : myEntries) {
            if (myEntry == null)
                continue;
            for (MyEntry<K,V> m = myEntry;m != null; m = m.getNext()) {
                set.add(myEntry);
            }
        }
        return set;
    }

    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<String, String>();
        map.put("1", "fcs1");
        map.put("2", "fcs2");
        map.put("2", "fcs22");
        map.put("3", "fcs3");
        System.out.println(map);
        System.out.println(map.get("3"));
        System.out.println(map.size());

    }
}
