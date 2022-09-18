package com.manzar.collections.stack;

public interface Stack<T> {

    void push(T item);

    void remove(int index);

    T peek();

    T pop();

    void clear();

    int size();

}
