package com.britesnow.j8.DevTestBao;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;
/**
 * Created by Bao on 2014/9/24.
 */
public class FunctionTest {
        private class PersonInfo{
            String name;
            Integer age, height, weight;
             PersonInfo(String name,Integer age,Integer height,Integer weight){
                this.name = name;
                this.age  = age;
                this.height = height;
                this.weight = weight;
            }
        }
        @Test
        public void firstTest(){
            PersonInfo myInfo = new PersonInfo("marx",24,175,60);

            Function<String, String>  myName = x -> x.substring(0,1).toUpperCase()+x.substring(1);
            Function<Integer, Integer> myBirth = x -> 2014 - x;
            Function<Integer, String> myHeight = x -> x.toString();
            Function<Integer, String >myWeight = x -> x.toString();

            System.out.println("My name is "+myName.apply(myInfo.name));
            System.out.println("I was born in "+myBirth.apply(myInfo.age).toString());
            System.out.println("I am "+myHeight.apply(myInfo.height)+" cm tall");
            System.out.println("And my weight is about "+myWeight.apply(myInfo.weight)+" kg");
        }
        @Test
        public void secondTest(){
            List<String> names = Arrays.asList("wangX","wangH","wangG","jeremy","bao");

            Function<List<String>, List<String>> filter = x -> x.stream().filter(e->e.length()<6).collect(Collectors.toList());
            Function<List<String>, List<String>> limit = x -> x.stream().limit(2).collect(Collectors.toList());
            Function<List<String>, List<String>> filterW = x -> x.stream().filter(e->e.contains("wang")).collect(Collectors.toList());

            System.out.println(filter.apply(names));
            System.out.println(filter.andThen(limit).apply(names));
            System.out.println(filter.compose(filterW).apply(names));
            System.out.println(limit.andThen(filter).compose(filterW).apply(names));
            System.out.println(filter.compose(limit).andThen(filterW).apply(names));
        }
}

