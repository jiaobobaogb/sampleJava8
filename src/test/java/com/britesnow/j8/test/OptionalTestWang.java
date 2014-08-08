package com.britesnow.j8.test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import org.junit.Test;

/**
 * <p>
 * </p>
 */
public class OptionalTestWang {

    @Test
    public void simpleOptional() {
        String mayBeNull = null;
        Optional<String> opt1 = Optional.of("Hello, Wang!");
        Optional<String> opt2 = Optional.ofNullable(mayBeNull);
        Optional<String> opt3 = Optional.empty();

        // ifPresent
        System.out.println("\nifPresent:\n");
        opt1.ifPresent(System.out::println);
        opt2.ifPresent(System.out::println);
        opt3.ifPresent(System.out::println);

        // isPresent
        System.out.println("\nisPresent:\n");
        boolean b1 = opt1.isPresent();
        boolean b2 = opt2.isPresent();
        boolean b3 = opt3.isPresent();
        System.out.println(b1 + "\t" + b2 + "\t" + b3);

        // filter
        System.out.println("\nfilter:\n");
        opt1.filter(x -> x.contains("Wang")).ifPresent(System.out::println);
        opt2.filter(x -> x.contains("Wang")).ifPresent(System.out::println);
        opt3.filter(x -> x.contains("Wang")).ifPresent(System.out::println);

        // map
        System.out.println("\nmap:\n");
        opt1.map(String::toUpperCase).ifPresent(System.out::println);

        // flatMap
        System.out.println("\nflatMap:\n");
        Optional<String> lastName = Optional.of("last");
        Optional<String> firstName = Optional.of("first");
        Optional<String> fullName = lastName.flatMap(ln -> firstName.map(fn -> String.join("", ln, fn)));
        System.out.println(fullName.get());

        Optional<Integer> len = opt1.map(String::length);
        // get
        System.out.println("\nget:\n");
        System.out.println(len.get());
        try {
            System.out.println(opt2.get());
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        }

        // orElse
        System.out.println("\norElse:\n");
        int len1 = opt1.map(String::length).orElse(-1);
        int len2 = opt2.map(String::length).orElse(-1);
        int len3 = opt3.map(String::length).orElseGet(() -> 5 * 40);
        System.out.println(len1 + "\t" + len2 + "\t" + len3);
        try {
            opt3.filter(s -> !s.isEmpty()).map(s -> s.charAt(0)).orElseThrow(IllegalArgumentException::new);
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        }

        // Optional to List
        System.out.println("\nto List:\n");
        List<String> list1 = opt1.map(Collections::singletonList).orElse(Collections.emptyList());
        System.out.println(list1.get(0));
        
        // OptionalInt
        System.out.println("\nOptionalInt:\n");
        OptionalInt optionalInt = OptionalInt.of(90);
        System.out.println(optionalInt.getAsInt());
    }
}
