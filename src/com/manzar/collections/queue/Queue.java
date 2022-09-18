package com.manzar.collections.queue;

public interface Queue<T> {

    void add(T t);

    T remove(int index);

    void clear();

    int size();

    T peek();

    T poll();
}
