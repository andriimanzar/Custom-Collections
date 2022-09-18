package com.manzar.collections.map;

public interface Map<K, V> {

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    void clear();

    int size();


}
