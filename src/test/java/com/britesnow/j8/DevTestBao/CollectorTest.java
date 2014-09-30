package com.britesnow.j8.DevTestBao;

import com.britesnow.j8.Teacher;
import com.sun.javafx.collections.MappingChange;
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

//    @Test
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
        teacherVector.stream().forEach(System.out::println);
    }
    @Test
    public void defaultCollectTest(){
        //toList
        List<Teacher> teachers =  Stream.of(teacherArray).map(data -> new Teacher(data[0],data[1],data[2])).collect(Collectors.toList());
        //toSet
        Set<String> stringSet = teachers.stream().map(item -> item.getId()).collect(Collectors.toSet());
        System.out.println(stringSet);
        //toMap
        Map<String,String> teacherMap = new HashMap<>();
        teacherMap = teachers.stream().filter(item -> item.getId()!="003").collect(Collectors.toMap(Teacher::getId, Teacher::getName));
        System.out.println(teacherMap);
        //joining
        String joining = teachers.stream().map(item -> item.getName()).collect(Collectors.joining("!!!"));
        System.out.println(joining);
        //groupingBy
        Map<String,List<Teacher>> teacherMap1 = teachers.stream().collect(Collectors.groupingBy(Teacher::getSex));
        System.out.println(teacherMap1);
        //averagingDouble
        Double teacherDouble = teachers.stream().collect(Collectors.averagingDouble(item -> Double.parseDouble(item.getId())));
        System.out.println(teacherDouble);
        //mapping
        List<String> teacherList = teachers.stream().collect(Collectors.mapping(Teacher::getName,Collectors.toList() ));
        System.out.println(teacherList);
        //collectingAndThen
        Teacher result = teachers.stream().collect(Collectors.collectingAndThen(Collectors.minBy((x,y) -> x.getName().length()>y.getName().length()?1:0), min -> min.get()));
        System.out.println(result);
    }
}
