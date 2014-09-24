package com.britesnow.j8.DevTestBao;

import org.junit.Test;

import java.util.Arrays;;
import java.util.List;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * Created by Bao on 2014/9/24.
 */
public class LambdaTest {
    @Test
    public void sortTest(){
        List<Integer> numbs = Arrays.asList(101,22,66,5,99);
        Collections.sort(numbs,(a,b)->a.compareTo(b));
        System.out.println(numbs);
    }
    @Test
    public void filterTest(){
        List<String> names = Arrays.asList("Bao","WangX","WangH","WangG","Jeremy","Kim");
        Predicate<String> matched = name -> name.contains("W");
        List<String> filterName = names.parallelStream().filter(matched).collect(Collectors.toList());
        System.out.println(filterName);
    }
}
