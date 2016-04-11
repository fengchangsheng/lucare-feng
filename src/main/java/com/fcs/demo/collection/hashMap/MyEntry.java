package com.fcs.demo.collection.hashMap;

import java.util.Map;

import static javafx.scene.input.KeyCode.M;

/**
 * Created by Lucare.Feng on 2016/4/11.
 */
public class MyEntry<K, V> implements Map.Entry<K, V> {

    private K key;
    private V value;
    private MyEntry<K, V> next;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public MyEntry(K key, V value, MyEntry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V v) {
        V result = value;
        value = v;
        return result;
    }

    public int hashCode() {
        return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    public boolean equals(Object o) {
        if (!(o instanceof MyEntry)) return false;
        MyEntry me = (MyEntry) o;
        return (key == null ? me.getKey() == null : key.equals(me.getKey())) && (value == null ? me.getValue() == null : value.equals(me.getValue()));
    }

    public String toString() {
        return key + "=" + value;
    }
}
