package com.britesnow.j8.test;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.britesnow.j8.Project;

/**
 * <p></p>
 */
public class StreamTestWang {
    
    static String[][] projectNamesArray = {
                            { "1", "First Project", "Cisco" },
                            { "2", "Second Project", "Cisco" },
                            { "3", "Third Project", "Google" },
                            { "4", "Fourth Project", "Google" },
                            { "5", "Fifth Project", "StrongMails" },
                            { "6", "Sixth Project", "StrongMails" },
                            { "7", "Seventh Project", "SalesForce" },
                            { "3", "Third Project", "Google" }
                    };

//	@Test
	public void testSimpleStream(){
        
	    
        List<Project> projects = Stream.of(projectNamesArray).map(data -> new Project(data[0], data[1], data[2])).collect(Collectors.toList());
        System.out.println("Init projects:\n" + projects + "\n");
        
        List<Project> results = null;
        Project project = null;
        
        // Remove duplicate
        // According to Object.equals(Object)
        projects = projects.stream().distinct().collect(Collectors.toList());
        System.out.println("Distinct projects:\n" + projects + "\n");
        
        // concat Stream
        results = Stream.concat(projects.stream(), projects.stream()).collect(Collectors.toList());
        System.out.println("Copy projects:\n" + results + "\n");
        
        // findAny
        project = projects.stream().filter(item -> item.getClient().equals("Google")).findAny().get();
        System.out.println("Get a project:\n" + project + "\n");
        
        // flatMap stream
        Stream<String> stream = Stream.of(projectNamesArray).flatMap(x -> Stream.of(x));
        System.out.println("flatMap:[ ");
        stream.forEach(System.out::println);
        System.out.println("]\n");
        
        // generate stream
        final List<Project> supplyProjects = projects;
        Stream<Project> stream1 = Stream.generate(() -> supplyProjects.get((int) Math.round(Math.random() * 4))).limit(4);
        System.out.println("generate stream:[ ");
        stream1.forEach(System.out::println);
        System.out.println("]\n");
        
        // peek element
        projects.stream().peek(item -> System.out.println("Before Map:" + item)).map(item -> item.getName()).peek(item -> System.out.println("After Map:" + item)).collect(Collectors.toList());
        System.out.println("\n");
        
        // reduce stream
        Optional<String> reduced = projects.stream().map(item -> item.getName()).reduce((a1, a2) -> a1 + "###" + a2);
        reduced.ifPresent((r) -> System.out.println("reduced stream: " + r +"\n"));
        
        
	}
	
//	@Test
    public void testSimpleColletor(){
	    //To List
        List<Project> projects = Stream.of(projectNamesArray).map(data -> new Project(data[0], data[1], data[2])).collect(Collectors.toList());
        System.out.println("Init projects:\n" + projects + "\n");
        
        //To Map
        Map<String, Project> resultMap = projects.stream().distinct().collect(Collectors.toMap(Project::getId, Function.identity()));
        System.out.println("Project map:\n" + resultMap + "\n");
        
        Map<String, String> stringResultMap = projects.stream().distinct().collect(Collectors.toMap(Project::getId, Project::getName));
        System.out.println("String map:\n" + stringResultMap + "\n");
        
        resultMap = projects.stream().collect(Collectors.toMap(Project::getId,  Function.identity(), (u, v) -> v));
        System.out.println("Merge map:\n" + resultMap + "\n");
        
        Map<String, Project> testMap = new HashMap();
        testMap.put("8", new Project("8", "Eighth Project", "Cisco"));
        resultMap = projects.stream().collect(Collectors.toMap(Project::getId,  Function.identity(), (u, v) -> v, () -> {return testMap;}));
        System.out.println("String map:\n" + resultMap + "\n");
        
        Double resultDouble = projects.stream().collect(Collectors.averagingLong(item -> Long.parseLong(item.getId())));
        System.out.println("average value:\n" + resultDouble + "\n");
        
        String resultString = projects.stream().map(item -> item.getName()).collect(Collectors.joining("#"));
        System.out.println("join value:\n" + resultString + "\n");
        
        Optional<Project> resultOptional = projects.stream().collect(Collectors.maxBy((s1, s2) -> s1.getName().length() > s2.getName().length() ? 1 : -1  ));
        System.out.println("max value:\n" + resultOptional + "\n");
        
        resultDouble = projects.stream().collect(Collectors.mapping(Project::getId, Collectors.averagingLong(item -> Long.parseLong((String) item))));
        System.out.println("average value:\n" + resultDouble + "\n");
        
        Map<String, List<Project>> resultProjects = projects.stream().collect(Collectors.groupingBy(Project::getClient));
        System.out.println("projects group value:\n" + resultProjects + "\n");
        
        Map<String, Optional<Project>> stringResultMapOptional = projects.stream().collect(Collectors.groupingBy(Project::getClient, Collectors.maxBy((s1, s2) -> ((Project) s1).getName().length() > ((Project) s2).getName().length() ? 1 : -1 )));
        System.out.println("projects group max optional project:\n" + stringResultMapOptional + "\n");
        
        resultMap = projects.stream().collect(Collectors.groupingBy(Project::getClient, Collectors.collectingAndThen(Collectors.maxBy((s1, s2) -> ((Project) s1).getName().length() > ((Project) s2).getName().length() ? 1 : -1 ), max -> max.get())));
        System.out.println("projects group max project:\n" + resultMap + "\n");
        
        Set<Project> resultSet = projects.stream().collect(Collectors.toSet());
        System.out.println("Project set:\n" + resultSet + "\n");
        
        Set<String> resultTreeSet = projects.stream().map(Project::getName).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("New Project set:\n" + resultTreeSet + "\n");
        
	}
	
	@Test
    public void testCustomColletor(){
	    List<Project> projects = Stream.of(projectNamesArray).map(data -> new Project(data[0], data[1], data[2])).distinct().collect(Collectors.toList());
        System.out.println("Init projects:\n" + projects + "\n");
        
        //to Stack
        Stack<Project> projectStacks = projects.stream().collect(Collectors.toCollection(Stack::new));
        System.out.println("Stack projects:\n" + projectStacks + "\n");
        
        projectStacks = projects.parallelStream().collect(new Collector<Project,Stack,Stack<Project>>(){

            @Override
            public Supplier supplier() {
                return Stack::new;
            }

            @Override
            public BiConsumer<Stack, Project> accumulator() {
                return Stack::push;
            }

            @Override
            // use when it is parallelStream
            public BinaryOperator<Stack> combiner() {
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
        
        System.out.println("Custom stack projects:\n" + projectStacks + "\n");
	}
	
}
