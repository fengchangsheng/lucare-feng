package com.fcs.demo.collection.hashMap;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Set;

import static javafx.scene.input.KeyCode.L;

/**
 * Created by Lucare.Feng on 2016/4/11.
 */
public class MyHashMap<K,V> extends AbstractMap<K,V>{

    private int size = 16;
    private MapEntry<K,V> mapEntry;

    @Override
    public V get(Object key) {
        return super.get(key);
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
