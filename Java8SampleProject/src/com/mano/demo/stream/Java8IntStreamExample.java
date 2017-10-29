package com.mano.demo.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8IntStreamExample {
	public static void main(String[] args) {

		IntStream.range(0, 10).forEach(i -> {
			if (i % 2 == 0)
				System.out.println(i);
		});

		IntStream.range(0, 10).filter(i -> i % 2 == 1).forEach(System.out::println);

		OptionalInt reduce = IntStream.range(0, 5).reduce((a, b) -> a + b);
		System.out.println(reduce.getAsInt());

		int[] ints = { 1, 2, 3, 4, 5 };
		Arrays.stream(ints).average().ifPresent(System.out::println);

		IntStream.builder().add(1).add(2).add(3).add(4).build().average().ifPresent(System.out::println);

		IntStream.range(0, 10).average().ifPresent(System.out::println);

		Stream.of(new BigDecimal("1.2"), new BigDecimal("3.7")).mapToDouble(BigDecimal::doubleValue).average()
				.ifPresent(System.out::println);
		;
	}
}
