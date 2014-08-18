package com.britesnow.j8.learning;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * <p></p>
 */
public class FunctionTest {

	@Test
	public void testAndThen(){
	    Function<Integer, Integer> adds = x -> x + 2;
	    Function<Integer, Integer> times = x -> x * 3;
	    
	    // test simple AndThen
	    System.out.println("Test (adds) => 8 + 2 = " + adds.apply(8));
	    System.out.println("Test (times) => 8 * 3 = " + times.apply(8));
	    System.out.println("Test (adds and times) => (8 + 2) * 3 = " + adds.andThen(times).apply(8));
	    System.out.println("Test (times and adds) => (8 * 3) + 2 = " + times.andThen(adds).apply(8));
	}
	
	@Test
    public void testCompose(){
        Function<Integer, Integer> adds = x -> x + 2;
        Function<Integer, Integer> times = x -> x * 3;
        
        // test simple compose
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
	    
	    // test complicate compose and andThen
	    System.out.println("Test andThen (adds and times) => (8 - 5) / 4 * 3 + 2 = " + adds.compose(times).compose(divide).compose(reduce).apply(8f));
	    System.out.println("Test andThen (adds and times) => (8 + 2) * 3 / 4 - 5 = " + adds.andThen(times).andThen(divide).andThen(reduce).apply(8f));
	    System.out.println("Test andThen (adds and times) => ((8 * 3) + 2) / 4 - 5 = " + adds.compose(times).andThen(divide).andThen(reduce).apply(8f));
	    System.out.println("Test andThen (adds and times) => ((8 - 5) * 3 + 2) / 4 = " + adds.compose(times).andThen(divide).compose(reduce).apply(8f));
	    System.out.println("Test andThen (adds and times) => (8 / 4 + 2) * 3 - 5 = " + adds.andThen(times).compose(divide).andThen(reduce).apply(8f));
	}
	
	@Test
    public void testConsumer(){
	    Consumer<Integer> consumer = System.out::println;
	    System.out.println("Consumer begin");
	    
	    //test Consumer Function
	    consumer.accept(1);
	    Consumer<Integer> consumer1 = t -> {
	        System.out.println("Go to andThen");
	        System.out.println(t);
	    };
	    consumer.andThen(consumer1).accept(2);
    }
	
	@Test
	public void testPredicate(){
	    Predicate<Integer> predicate = t -> { return t >= 0 ? true : false;};
	    System.out.println("Predicate begin");
	    System.out.println(predicate.test(1));
	    Predicate<Integer> predicate1 = t -> { return t % 2 == 0 ? true : false;};
	    
	    // Test Predicate function api
	    System.out.println(predicate.negate().test(3));
	    System.out.println(predicate.and(predicate1).test(1));
	    System.out.println(predicate.or(predicate1).test(1));
	    System.out.println(Predicate.isEqual(2).test(2));
	    System.out.println(Predicate.isEqual(2).test(1));
	}
	
	@Test
	public void testSupplier(){
	    Supplier<Integer> supplier = () -> { return 1;};
	    Supplier<Integer> supplier1 = () -> { return 2;};
	    System.out.println("Supplier begin");
	    // Test Supplier function 
	    System.out.println(supplier.get() + supplier1.get());
	}
	
	@Test
	public void testBinaryOperator(){
	    BinaryOperator<Integer> binaryOperator = (a, b) -> { return a + b;};
	    // Test BinaryOperator function 
	    System.out.println("BinaryOperator begin");
	    System.out.println(binaryOperator.apply(1, 2));
	}
	
	@Test
	public void testTwoParams(){
	    BiFunction<Integer, String, Boolean> biFunction = (a, b) -> { return a + Integer.valueOf(b) > 0 ? true : false;};
	    System.out.println("BiFunction begin");
	    // Test BiFunction function 
	    System.out.println(biFunction.apply(-1, "2"));
	    
	    BiConsumer<Integer, String> biConsumer = (a, b) -> {System.out.println(a + b);};
        System.out.println("BiConsumer begin");
        biConsumer.accept(1, "2");

        BiPredicate<Integer, String> biPredicate = (a, b) -> {return a < Integer.valueOf(b) ? true : false;};
        System.out.println("BiPredicate begin");
        System.out.println(biPredicate.test(1, "2"));
	}
	
}
