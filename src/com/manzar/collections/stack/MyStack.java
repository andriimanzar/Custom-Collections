package com.manzar.collections.stack;

import java.util.EmptyStackException;
import java.util.Objects;

public class MyStack<T> implements Stack<T> {

    private int size;
    private Node<T> head;

    @Override
    public void push(T item) {
        Objects.requireNonNull(item);
        Node<T> newNode = new Node<>(item);
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    @Override
    public T pop() {
        checkStackIsNotEmpty();
        Node<T> removedNode = head;
        Node<T> nextNode = head.next;
        T value = removedNode.value;
        head = nextNode;
        size--;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(int index) {
        checkStackIsNotEmpty();
        Objects.checkIndex(index, size);
        Node<T> nodeToRemove = node(index);
        removeNode(nodeToRemove, index);

    }

    @Override
    public T peek() {
        checkStackIsNotEmpty();
        return head.value;
    }

    @Override
    public void clear() {
        for (Node<T> tmp = head; tmp != null; ) {
            Node<T> next = tmp.next;
            tmp.next = null;
            tmp = next;
        }
        head = null;
        size = 0;
    }

    private void removeNode(Node<T> node, int index) {
        Node<T> previous = node(index - 1);
        Node<T> next = node.next;
        node.value = null;
        previous.next = next;
        if (index == 0) {
            head = next;
        }
        size--;
    }


    private Node<T> node(int index) {
        Node<T> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    private void checkStackIsNotEmpty() {
        if (size == 0) {
            throw new EmptyStackException();
        }
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

}
