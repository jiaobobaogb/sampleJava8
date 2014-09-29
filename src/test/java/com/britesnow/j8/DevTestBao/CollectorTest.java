package com.britesnow.j8.DevTestBao;

import com.britesnow.j8.Teacher;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Bao on 2014/9/26.
 */
public class CollectorTest {
    static String[][] teacherArray = {
            { "001", "Mary", "female" },
            { "002", "Christina", "female" },
            { "003", "Meimei Han", "female" },
            { "004", "Barbara", "male" },
            { "005", "Jerry", "male" },
    };

    @Test
    public void customCollectTest(){
        List<Teacher> teachers =  Stream.of(teacherArray).map(data -> new Teacher(data[0],data[1],data[2])).collect(Collectors.toList());
        teachers.stream().forEach(System.out::println);
        //toVector
        Vector<Teacher> teacherVector = teachers.stream().collect(Collectors.toCollection(Vector::new));
        System.out.println(teacherVector);
        teacherVector = teachers.parallelStream().collect(new Collector<Teacher,Vector,Vector<Teacher>>(){

            @Override
            public Supplier supplier() {
                return Vector::new;
            }

            @Override
            public BiConsumer<Vector, Teacher> accumulator() {
                return Vector::add;
            }

            @Override
            // use when it is parallelStream
            public BinaryOperator<Vector> combiner() {
                return (left, right) -> {
                    right.addAll(left);
                    return right;
                };
            }

            @Override
            public Function finisher() {
                return a -> a;
            }

            @Override
            public Set characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
            }

        });
        System.out.println(teacherVector);
    }
}
