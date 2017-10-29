package com.sample.mano.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8IntStreamExample {
	public static void main(String[] args) {
		// IntStream : 1
		IntStream.range(0, 10).forEach(System.out::println);

		// IntStream : 2
		IntStream.range(0, 10).forEach(a -> {
			if (a % 2 == 0)
				System.out.println(a);
		});
		IntStream.range(0, 10).filter(a -> a % 2 == 0).forEach(System.out::println);

		// IntStream : 3
		OptionalInt sum = IntStream.range(1, 6).reduce((a, b) -> a + b);
		sum.ifPresent(System.out::println);

		// IntStream : 4
		int reduce2 = IntStream.range(0, 5).reduce(7, (a, b) -> a + b);
		System.out.println("reduce2::" + reduce2);

		// IntStream : 5
		int[] ints = { 1, 3, 5, 7, 11 };
		OptionalDouble res = Arrays.stream(ints).average();
		res.ifPresent(System.out::println);

		// IntStream : 6

		Stream.of(new BigDecimal("1.2"), new BigDecimal("3.5")).mapToDouble(BigDecimal::doubleValue).average()
				.ifPresent(System.out::println);
	}
}
