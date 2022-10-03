package com.manzar.collections.list.linkedlist;

import com.manzar.collections.list.List;

import java.util.Objects;

public class MyLinkedList<T> implements List<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public boolean add(T t) {
        Node<T> newNode = new Node<>(t);
        if (size == 0) {
            head = tail = newNode;
            head.prev = null;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        tail.next = null;
        size++;
        return true;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        return unlink(node(index));
    }


    @Override
    public void clear() {
        for (Node<T> tmp = head; tmp != null; ) {
            Node<T> next = tmp.next;
            tmp.next = null;
            tmp.prev = null;
            tmp.value = null;
            tmp = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return node(index).value;
    }

    @Override
    public int size() {
        return size;
    }

    private T unlink(Node<T> unlinkedNode) {
        final T value = unlinkedNode.value;
        final Node<T> prev = unlinkedNode.prev;
        final Node<T> next = unlinkedNode.next;

        unlinkFromPreviousNode(unlinkedNode, prev, next);
        unlinkFromNextNode(unlinkedNode, next, prev);

        unlinkedNode.value = null;
        size--;
        return value;
    }

    private void unlinkFromPreviousNode(Node<T> node, Node<T> prev, Node<T> next) {
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
    }

    private void unlinkFromNextNode(Node<T> node, Node<T> next, Node<T> prev) {
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
    }

    private Node<T> node(int index) {
        Node<T> result;
        if (index < (size / 2)) {
            result = head;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            result = tail;
            for (int i = size - 1; i > index; i--) {
                result = result.prev;
            }
        }
        return result;
    }

    private static class Node<T> {

        private T value;
        private Node<T> prev;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
