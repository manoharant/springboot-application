package com.mano.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8StreamToCollection {
	public static void main(String[] args) {
		// Type1:
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		List<Integer> myList = stream.collect(Collectors.toList());
		myList.forEach(System.out::println);

		// Type2:
		stream = Stream.of(1, 2, 3);
		Map<Integer, Integer> myMap = stream.collect(Collectors.toMap(i -> i, i -> i + 1));
		System.out.println(myMap);

		// Type3:
		Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5);
		Integer[] intArr = stream1.toArray(Integer[]::new);
		System.out.println(Arrays.toString(intArr));
	}
}
