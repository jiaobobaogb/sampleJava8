package com.britesnow.j8.DevTestBao;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bao on 2014/9/25.
 */
public class MapTest {
    @Test
    public void MapTest(){
        Map<Integer, String> personId = new HashMap<Integer, String>();
        personId.putIfAbsent(1, "Bao");
        personId.putIfAbsent(2, "Wang");
        personId.putIfAbsent(3, "Kim");

        personId.putIfAbsent(1, "Jay");
        System.out.println(personId.get(1));
        personId.put(1, "Bob");
        System.out.println(personId+"\n--------------");

        personId.computeIfPresent(2, (key, val) -> val + key);
        System.out.println(personId);
        personId.computeIfPresent(0, (key, val) -> val = "Amy");
        System.out.println(personId+"\n--------------");

        personId.computeIfAbsent(3, (key) -> "Kimmy");
        System.out.println(personId);
        personId.computeIfAbsent(0, (key) -> "Amy");
        System.out.println(personId+"\n--------------");

        System.out.println(personId.getOrDefault(1, "notFound"));
        System.out.println(personId.getOrDefault(7, "notFound")+"\n--------------");

        personId.remove(1, "Bao");
        System.out.println(personId);
        personId.remove(1, "Bob");
        System.out.println(personId+"\n--------------");

        personId.merge(1, "Bob", (val, newVal) -> val + newVal);
        System.out.println(personId.get(1));
        personId.merge(1, "Bob", (val, newVal) -> val + newVal);
        System.out.println(personId.get(1));
    }
}
