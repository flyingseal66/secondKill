package org.secondKill.collections;

import java.util.*;

public class IfNull {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        Set<String> set = new TreeSet<>();


        Map<String, String> map = new HashMap<>();

        list.add(null);
        list.add(null);
        list.add("list");

        set.add(null);
        set.add(null);
        set.add("set");

        map.put(null, null);
        map.put(null,"666");
        map.put("map", "map");

        System.out.println(list.size());

        System.out.println(set.size());

        System.out.println(map.get(null));



    }
}
