package com.britesnow.j8.test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.britesnow.j8.Project;

/**
 * <p></p>
 */
public class StreamTestWang {

	@Test
	public void testSimpleStream(){
        String[][] projectNamesArray = {
                { "1", "First Project", "Cisco" },
                { "2", "Second Project", "Cisco" },
                { "3", "Third Project", "Google" },
                { "4", "Fourth Project", "Google" },
                { "5", "Fifth Project", "StrongMails" },
                { "6", "Sixth Project", "StrongMails" },
                { "7", "Seventh Project", "SalesForce" },
                { "3", "Third Project", "Google" }
        };
	    
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
        
        
        //Colletors
        
        Map<String, Project> resultMap = projects.stream().collect(Collectors.toMap(Project::getId, Function.identity()));
        System.out.println("Project map:\n" + resultMap + "\n");
        
        Set<Project> resultSet = projects.stream().collect(Collectors.toSet());
        System.out.println("Project set:\n" + resultSet + "\n");
        
	}
	
}
