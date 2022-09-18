package com.manzar.collections.stack;

public class MyStackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new MyStack<>();

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        System.out.println(stack.peek());

        stack.remove(5);

        System.out.println("~~~~~~~~~~~~~~~~");

        System.out.println("Stack size before clear(): " + stack.size());
        stack.clear();
        System.out.println("Stack size after clear(): " + stack.size());
    }
}
