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

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();
        for (LinkedList<MapEntry<K,V>> linkedList:buckets){
//            if (linkedList != null){
                for (MapEntry<K, V> mapEntry : linkedList) {
                    set.add(mapEntry);
                }
//            }
        }
        return set;
    }

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % size;
        if (buckets[index] == null) {
            throw new NoSuchElementException();
        }else {
            LinkedList<MapEntry<K, V>> bucket = buckets[index];
            ListIterator<MapEntry<K, V>> iterator = bucket.listIterator();
            while (iterator.hasNext()) {
                MapEntry<K,V> pair = iterator.next();
                if (pair.getKey().equals(key)) {
                    return pair.getValue();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SimpleHashMap<String,Integer> simpleHashMap = new SimpleHashMap<String, Integer>();
        simpleHashMap.put("fcs", 0);
        simpleHashMap.put("fcs1", 1);
//        simpleHashMap.put("fcs2", 2);
//        simpleHashMap.put("fcs3", 3);
        System.out.println(simpleHashMap);

    }
}
