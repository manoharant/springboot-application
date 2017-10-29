package com.sample.mano.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8StreamCreation {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Arrays.asList("a1", "b1", "c1", "d1", "z1").stream().findFirst().ifPresent(System.out::println);

		Stream.of("a1", "a2", "b1", "b2", "c1", "c2").map(a -> a.substring(1)).mapToInt(Integer::parseInt).max()
				.ifPresent(System.out::println);
		IntStream.range(0, 5).mapToObj(num -> "Obj" + num).forEach(System.out::println);

		Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(num -> "Test" + num).forEach(System.out::println);
	}
}
