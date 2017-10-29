package com.sample.mano.time.basics;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8StreamBasics {

	public static void main(String[] args) {
		// sortMap();
		testArray();
	}

	public static void sortMap() {
		Map<String, Integer> unsortMap = new HashMap<>();
		unsortMap.put("z", 10);
		unsortMap.put("b", 5);
		unsortMap.put("a", 6);
		unsortMap.put("c", 20);
		unsortMap.put("d", 1);
		unsortMap.put("e", 7);
		unsortMap.put("y", 8);
		unsortMap.put("n", 99);
		unsortMap.put("g", 50);
		unsortMap.put("m", 2);
		unsortMap.put("f", 9);

		System.out.println("Original...");
		System.out.println(unsortMap);

		Map<String, Integer> sortByKey = unsortMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(
				Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (obj1, obj2) -> obj1, LinkedHashMap::new));

		Map<String, Integer> sortByVal = unsortMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
				Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (obj1, obj2) -> obj1, LinkedHashMap::new));

		// let's sort this map by keys first
		Map<String, Integer> sorted = unsortMap.entrySet().stream().sorted(comparingByKey())
				.collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

		System.out.println(sortByKey);
		System.out.println(sortByVal);
		System.out.println(sorted);
	}

	public static void testArray() {
		String[] array = {"a", "b", "c", "d", "e"};
		
		Stream<String> stream1 = Arrays.stream(array);
		stream1.forEach(System.out::println);
	}
}
