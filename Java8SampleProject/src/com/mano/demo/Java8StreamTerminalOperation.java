package com.mano.demo;

import java.util.Optional;
import java.util.stream.Stream;

public class Java8StreamTerminalOperation {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Type : reduce
		Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
		Optional<Integer> intOptional = numbers.reduce((i, j) -> {
			return i * j;
		});

		if (intOptional.isPresent())
			System.out.println("Multiplication= " + intOptional.get());

		// Type : count

		Stream<Integer> streamInt = Stream.of(1, 2, 3, 4, 5);
		System.out.println("No of elements in stream= " + streamInt.count());

		// Type : forEach

		Stream<Integer> streamForEach = Stream.of(1, 2, 3, 4, 5, 6, 7, 8);
		streamForEach.forEach(System.out::println);

		// Type : match

		Stream<Integer> number = Stream.of(1, 2, 3, 4, 5, 6, 7);
		System.out.println("Any Match :: " + number.anyMatch(n -> n == 4));
		number = Stream.of(1, 2, 3, 4, 5, 6, 7);
		System.out.println("All Match ::" + number.allMatch(n -> n < 8));
		number = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		System.out.println("None Match ::" + number.noneMatch(n -> n == 10));

		// Type : findFirst

		Stream<String> strStream = Stream.of("Mano", "Edmund", "Michael", "Krish", "Vish");
		Optional<String> nameStartsWithD = strStream.filter(str -> str.startsWith("V")).findFirst();
		if (nameStartsWithD.isPresent())
			System.out.println("Matched ::" + nameStartsWithD.get());
	}
}
