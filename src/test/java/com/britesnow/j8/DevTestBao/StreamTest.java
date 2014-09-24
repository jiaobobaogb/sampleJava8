package com.britesnow.j8.DevTestBao;

import java.util.ArrayList;
import  java.util.List;
import  java.util.Map;
import  java.util.stream.Stream;
import  java.util.function.Function;

import  static java.util.stream.Collectors.*;

public class StreamTest{
    public void firstTest(){
        Integer[] nums= {1,1,null,2,3,4,null,5,6,7,8};
        List<Integer> numList = new ArrayList<>();
        for(int i :nums){
            numList.add(i);
        }
        System.out.println("sum is:"+numList.stream().filter(num -> num != null).
                distinct().mapToInt(num -> num*2).
                peek(System.out::println).skip(2).limit(4).sum()
        );

    }
}



