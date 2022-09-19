package com.manzar.collections.map;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

public class MyHashMap<K, V> implements Map<K, V> {

    public static final String INVALID_CAPACITY_MESSAGE = "Initial capacity must be greater than 0";

    private static final int DEFAULT_CAPACITY = 8;
    private static final float RESIZE_THRESHOLD = 1.0f;
    private Node<K, V>[] table;
    private int size;

    public MyHashMap(int initialCapacity) {
        validateCapacity(initialCapacity);
        this.table = new Node[initialCapacity];
    }

    public MyHashMap() {
        this.table = new Node[DEFAULT_CAPACITY];
    }


    private static class Node<K, V> {

        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static int calculateIndex(Object key, int tableCapacity) {
        var hash = key.hashCode() ^ (key.hashCode() >> 16);
        return hash & (tableCapacity - 1);
    }

    @Override
    public V put(K key, V value) {
        resizeIfNeeded();
        return addToTable(table, key, value);
    }

    private void resizeIfNeeded() {
        if (size / (float) table.length > RESIZE_THRESHOLD) {
            resizeTable(2 * table.length);
        }
    }

    private void resizeTable(int newSize) {
        Node<K, V>[] newTable = new Node[newSize];
        for (Node<K, V> entry : table) {
            Node<K, V> current = entry;
            while (current != null) {
                addToTable(newTable, current.key, current.value);
                current = current.next;
            }
        }
        table = newTable;
    }

    private V addToTable(Node<K, V>[] table, K key, V value) {
        Node<K, V> newNode = new Node<>(requireNonNull(key), requireNonNull(value));
        int index = calculateIndex(key, table.length);
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node<K, V> currentNode = table[index];
            while (currentNode.next != null) {
                if (currentNode.key.equals(key)) {
                    V previousValue = currentNode.value;
                    currentNode.value = value;
                    return previousValue;
                }
                currentNode = currentNode.next;
            }
            if (currentNode.key.equals(key)) {
                V previousValue = currentNode.value;
                currentNode.value = value;
                return previousValue;
            }
            currentNode.next = newNode;
        }
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(requireNonNull(key), table.length);
        Node<K, V> currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = calculateIndex(requireNonNull(key), table.length);
        Node<K, V> currentNode = table[index];
        if (currentNode != null) {
            if (currentNode.key.equals(key)) {
                V value = currentNode.value;
                table[index] = currentNode.next;
                size--;
                return value;
            }
            while (currentNode.next != null) {
                if (currentNode.next.key.equals(key)) {
                    V value = currentNode.next.value;
                    currentNode.next = currentNode.next.next;
                    size--;
                    return value;
                }
                currentNode = currentNode.next;
            }
        }
        return null;
    }

    public void clear() {
        Node<K, V>[] tab = table;
        if (size > 0) {
            size = 0;
            Arrays.fill(tab, null);
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void validateCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(INVALID_CAPACITY_MESSAGE);
        }
    }

}