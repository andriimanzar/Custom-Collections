package com.manzar.collections.map;

public class MyHashMapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new MyHashMap<>();

        map.put("first", 1);
        map.put("second", 2);
        map.put("someNumber", 3);
        map.put("someNumber", 4);

        System.out.println(map.get("someNumber"));

        map.remove("second");
        System.out.println(map.get("second"));

        System.out.println("~~~~~~~~~~~~~~~~~~~");

        System.out.println("Map size before clear(): " + map.size());
        map.clear();
        System.out.println("Map size after clear(): " + map.size());

    }
}
