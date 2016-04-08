package com.fcs.demo.collection.hashMap;

import java.util.*;

/**
 * Created by Lucare.Feng on 2016/4/8.
 */
public class SimpleHashMap<K,V> extends AbstractMap<K,V> {

    private int size = 17;
    private LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[size];

    SimpleHashMap(){

    }

    public V put(K key,V value){
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % size;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<MapEntry<K, V>>();
        }
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<K, V>(key,value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K,V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }
        if (!found)
            buckets[index].add(pair);
        return oldValue;
    }

//    public V get(K k) {
//
//    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
