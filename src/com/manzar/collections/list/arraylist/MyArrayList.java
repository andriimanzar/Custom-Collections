package com.manzar.collections.list.arraylist;

import com.manzar.collections.list.List;

import java.util.Objects;

public class MyArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initialCapacity) {
        validateCapacity(initialCapacity);
        elements = new Object[initialCapacity];
    }

    @Override
    public boolean add(T t) {
        resizeIfNeeded();
        elements[size] = t;
        size++;
        return true;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T deletedElement = (T) elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return deletedElement;
    }

    @Override
    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void resizeIfNeeded() {
        if (size == elements.length) {
            int newSize = size * 3 / 2 + 1;
            Object[] newElements = new Object[newSize];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    private void validateCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0");
        }
    }
}
