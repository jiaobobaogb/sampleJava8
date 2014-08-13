package com.britesnow.j8.learning;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.junit.Test;

public class LambdaTest {
    @Test
    public void lambdaTest(){
        
        BiFunction<Integer, Integer, Boolean> biFunction = new BiFunction<Integer, Integer, Boolean>(){
            @Override
            public Boolean apply(Integer t, Integer u) {
                return t > u;
            }
        };
        
        System.out.println("Original interface: " + biFunction.apply(3, 4));
        
        biFunction = (Integer t, Integer u) -> {return t > u;};
        System.out.println("Simpler: (Integer t, Integer u) -> {return t > u;}  ===== " + biFunction.apply(3, 4));
        biFunction = (t, u) -> {return t > u;};
        System.out.println("Simpler: (t, u) -> {return t > u;};  ===== " + biFunction.apply(3, 4));
        
        // for single params
        Predicate<Integer> predicate = new Predicate<Integer>(){
            @Override
            public boolean test(Integer t) {
                return t > 0;
            }
        };
        System.out.println("Original single interface: " + predicate.test(2));
        
        predicate = t -> {return t > 0;};
        System.out.println("Original single interface: t -> {return t > 0;} ===== " + predicate.test(2));
        
        Predicate<String> predicate1 = Boolean::valueOf;
        System.out.println("Original single interface: Boolean::valueOf ===== " + predicate1.test("true"));
    }
}
