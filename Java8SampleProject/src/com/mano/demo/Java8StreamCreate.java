package com.mano.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Java8StreamCreate {
	public static void main(String[] args) {
		// Type1:
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
		stream.forEach(System.out::println);

		// Type2:
		List<Integer> myList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		Stream<Integer> stream1 = myList.stream();
		stream1.forEach(System.out::println);

		Stream<Integer> stream3 = myList.parallelStream();
		stream3.forEach(System.out::println);
		// Type3:

		Stream<String> stream4 = Stream.generate(() -> {
			return "test";
		});
		stream4.limit(10).forEach(System.out::println);
		// Type4:

		IntStream stream5 = Arrays.stream(new int[] { 1, 2 });
		stream5.forEach(System.out::println);
		LongStream stream6 = Arrays.stream(new long[] { 1, 2, 3, 4 });
		stream6.forEach(System.out::println);
	}
}
