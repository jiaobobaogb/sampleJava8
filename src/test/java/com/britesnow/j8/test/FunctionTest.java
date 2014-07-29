package com.britesnow.j8.test;

import java.util.function.Function;

import org.junit.Test;

/**
 * <p></p>
 */
public class FunctionTest {

	@Test
	public void testAndThen(){
	    Function<Integer, Integer> adds = x -> x + 2;
	    Function<Integer, Integer> times = x -> x * 3;
	    
	    System.out.println("Test (adds) => 8 + 2 = " + adds.apply(8));
	    System.out.println("Test (times) => 8 * 3 = " + times.apply(8));
	    System.out.println("Test (adds and times) => (8 + 2) * 3 = " + adds.andThen(times).apply(8));
	    System.out.println("Test (times and adds) => (8 * 3) + 2 = " + times.andThen(adds).apply(8));
	}
	
	@Test
    public void testCompose(){
        Function<Integer, Integer> adds = x -> x + 2;
        Function<Integer, Integer> times = x -> x * 3;
        
        System.out.println("Test (adds) => 8 + 2 = " + adds.apply(8));
        System.out.println("Test (times) => 8 * 3 = " + times.apply(8));
        System.out.println("Test (adds and times) => (8 * 3) + 2 = " + adds.compose(times).apply(8));
        System.out.println("Test (times and adds) => (8 + 2) * 3 = " + times.compose(adds).apply(8));
    }
	
	@Test
	public void testComposeAndThen(){
	    Function<Float, Float> adds = x -> x + 2;
	    Function<Float, Float> times = x -> x * 3;
	    Function<Float, Float> divide = x -> x / 4;
	    Function<Float, Float> reduce = x -> x - 5;
	    
	    System.out.println("Test andThen (adds and times) => (8 - 5) / 4 * 3 + 2 = " + adds.compose(times).compose(divide).compose(reduce).apply(8f));
	    System.out.println("Test andThen (adds and times) => (8 + 2) * 3 / 4 - 5 = " + adds.andThen(times).andThen(divide).andThen(reduce).apply(8f));
	    System.out.println("Test andThen (adds and times) => ((8 * 3) + 2) / 4 - 5 = " + adds.compose(times).andThen(divide).andThen(reduce).apply(8f));
	    System.out.println("Test andThen (adds and times) => ((8 - 5) * 3 + 2) / 4 = " + adds.compose(times).andThen(divide).compose(reduce).apply(8f));
	    System.out.println("Test andThen (adds and times) => (8 / 4 + 2) * 3 - 5 = " + adds.andThen(times).compose(divide).andThen(reduce).apply(8f));
	}
}
