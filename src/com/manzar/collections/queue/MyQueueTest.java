package com.manzar.collections.queue;

public class MyQueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new MyQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        System.out.println("Removed object: " + queue.remove(5));

        System.out.println("~~~~~~~~~~~~~~~~");

        System.out.println("Queue size before clear(): " + queue.size());
        queue.clear();
        System.out.println("Queue size after clear(): " + queue.size());
    }
}
