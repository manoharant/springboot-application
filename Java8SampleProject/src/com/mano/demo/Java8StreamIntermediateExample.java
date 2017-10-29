package com.mano.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8StreamIntermediateExample {

	public static void main(String[] args) {
		List<Integer> myList = new ArrayList<>();
		for (int i = 0; i < 100; i++)
			myList.add(i);
		// Type : filter
		Stream<Integer> stream = myList.stream();
		Stream<Integer> streamHighNum = stream.filter(n -> n > 90);
		streamHighNum.forEach(p -> System.out.println("High numbers :" + p));

		// Type : map

		Stream<String> strStream = Stream.of("MAnoharan", "Stefan", "Michael", "Edmund");
		Stream<String> capitalStream = strStream.map(s -> s.toUpperCase());
		List<String> collectStr = capitalStream.collect(Collectors.toList());
		collectStr.forEach(System.out::println);

		// Type : sorted

		Stream<String> sortStream = Stream.of("Z", "K", "B", "L", "A");
		List<String> revSortedStr = sortStream.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(revSortedStr);

		Stream<String> sortStr = Stream.of("R", "D", "X", "J");
		List<String> sortList = sortStr.sorted().collect(Collectors.toList());
		System.out.println(sortList);

		// Type : flatMap

		Stream<List<String>> listStream = Stream.of(Arrays.asList("Michael", "Hassen"),
				Arrays.asList("David", "Beckam"));
		Stream<String> flatMapStream = listStream.flatMap(str -> str.stream());

		flatMapStream.forEach(System.out::println);
	}
}
