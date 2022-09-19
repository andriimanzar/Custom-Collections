package com.manzar.collections.queue;

import java.util.Objects;

public class MyQueue<T> implements Queue<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public T poll() {
        if (head != null) {
            Node<T> nextHead = head.next;
            T headValue = head.value;
            head.value = null;
            head.next = null;
            head = nextHead;
            size--;
            return headValue;
        } else {
            return null;
        }
    }

    @Override
    public T peek() {
        return head != null ? head.value : null;
    }

    @Override
    public void add(T t) {
        Node<T> newNode = new Node<>(t);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        Node<T> nodeToRemove = node(index);
        T result = nodeToRemove.value;
        removeNode(nodeToRemove, index);
        return result;
    }

    private void removeNode(Node<T> nodeToRemove, int index) {
        Node<T> nextNode = nodeToRemove.next;
        Node<T> previousNode = node(index - 1);
        if (index != 0 && index != size - 1) {
            previousNode.next = nextNode;
        } else if (index == 0) {
            head = nextNode;
        } else if (index == size - 1) {
            previousNode.next = null;
            tail = previousNode;
        }
        nodeToRemove.value = null;
        size--;
    }


    @Override
    public void clear() {
        for (Node<T> tmp = head; tmp != null; ) {
            Node<T> next = tmp.next;
            tmp.next = null;
            tmp.value = null;
            tmp = next;
        }
        head = null;
        size = 0;
    }

    private Node<T> node(int index) {
        if (index == size - 1) {
            return tail;
        } else {
            Node<T> result = head;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            return result;
        }
    }
}
