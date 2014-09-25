package com.britesnow.j8.DevTestBao;

import org.junit.Test;

/**
 * Created by Bao on 2014/9/25.
 */
public class InterfaceTest {
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
        default Integer length(F from){
            return from.toString().length();
        }
    }
    @Test
    public void InterfaceTest(){
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);
        Converter<Integer, String> calLength = String::valueOf;
        Integer length = calLength.length(123);
        System.out.println(length);
    }
}
