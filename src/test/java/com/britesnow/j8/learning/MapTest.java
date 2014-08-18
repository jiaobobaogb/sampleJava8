package com.britesnow.j8.learning;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * <p></p>
 */
public class MapTest {

	@Test
	public void simpleMapTest(){
	    Map<String, String> people = new HashMap<String, String>();
	    people.put("Joe", "Salvatori");
	    people.put("Ragazzo", "Robert");
	    people.put("Alexis", "Alekna");
	    
	    //putIfAbsent
	    people.putIfAbsent("Joe", "Diaz");
	    System.out.println(people);
	    people.put("Joe", "Diaz");
	    System.out.println(people);
	    
	    //computeIfPresent
	    people.computeIfPresent("Ragazzo", (key, val) -> val + key);
	    System.out.println(people);
	    people.computeIfPresent("Hubbell", (key, val) -> val + key);
	    System.out.println(people);
	    
	    //computeIfAbsent
	    people.computeIfAbsent("Ragazzo", (key) -> "NEW:" + key);
	    System.out.println(people);
	    people.computeIfAbsent("Hubbell", (key) -> "NEW:" + key);
	    System.out.println(people);
	    
	    //getOrDefault
	    System.out.println(people.getOrDefault("Joe", "Unknown"));
	    System.out.println(people.getOrDefault("Carl", "Unknown"));
	    
	    //remove
	    people.remove("Alexis", "test");
	    System.out.println(people);
	    people.remove("Alexis", "Alekna");
	    System.out.println(people);
	    
	    //merge
	    people.merge("Alexis", "Alekna", (val, newVal) -> val + "---" + newVal);
	    System.out.println(people);
	    people.merge("Alexis", "Alekna", (val, newVal) -> val + "---" + newVal);
	    System.out.println(people);
	    
	}

}
