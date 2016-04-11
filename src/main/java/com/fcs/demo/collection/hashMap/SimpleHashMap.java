package com.fcs.demo.collection.hashMap;

import javax.jws.Oneway;
import java.util.*;

/**
 * Created by Lucare.Feng on 2016/4/8.
 */
public class SimpleHashMap<K,V> extends AbstractMap<K,V> {

    private int size = 16;
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

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % size;
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        if (bucket == null) {
            return null;
        }else{
            for (MapEntry<K, V> mapEntry : bucket) {
                if (mapEntry.getKey().equals(key)) {
                    return mapEntry.getValue();
                }
            }
        }
        return null;
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();
        for (LinkedList<MapEntry<K, V>> linkedList : buckets) {
            if (linkedList == null)
                continue;
            for (MapEntry<K, V> mapEntry : linkedList) {
                set.add(mapEntry);
            }
        }
        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap<String, String> map = new SimpleHashMap<String, String>();
        map.put("1", "fcs1");
        map.put("2", "fcs2");
        map.put("3", "fcs3");
        System.out.println(map);
        System.out.println(map.get("3"));
        System.out.println(map.size());

    }
}
