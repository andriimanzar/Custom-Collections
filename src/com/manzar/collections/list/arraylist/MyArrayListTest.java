package com.manzar.collections.list.arraylist;

import com.manzar.collections.list.List;

public class MyArrayListTest {
    public static void main(String[] args) {
        List<Integer> list = new MyArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        list.remove(1);

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println("~~~~~~~~~~~~~~~~~~~");

        System.out.println("List size before clear(): " + list.size());
        list.clear();
        System.out.println("List size after clear(): " + list.size());

    }
}
