package com.manzar.collections.list;

public interface List<T> {

    boolean add(T value);

    T get(int index);

    T remove(int index);

    int size();

    void clear();
}
