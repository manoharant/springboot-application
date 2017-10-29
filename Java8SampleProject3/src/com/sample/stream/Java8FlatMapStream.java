package com.sample.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8FlatMapStream {
	public static void main(String[] args) {
		String[][] data = new String[][] { { "a", "b" }, { "c", "d" }, { "e", "f" } };

		// Stream<String[]>
		Stream<String[]> stream1 = Stream.of(data);
		// Stream<String>
		Stream<String> string = stream1.flatMap(s -> Arrays.stream(s));

		List<String> listStr = string.collect(Collectors.toList());
		System.out.println(listStr);
		// Example 2

		Student obj1 = new Student();
		obj1.setName("mkyong");
		obj1.addBook("Java 8 in Action");
		obj1.addBook("Spring Boot in Action");
		obj1.addBook("Effective Java (2nd Edition)");

		Student obj2 = new Student();
		obj2.setName("zilap");
		obj2.addBook("Learning Python, 5th Edition");
		obj2.addBook("Effective Java (2nd Edition)");

		List<Student> list = new ArrayList<>();
		list.add(obj1);
		list.add(obj2);

		List<String> bookNames = list.stream().map(b -> b.getBook()).flatMap(b -> b.stream())
				.collect(Collectors.toList());
		System.out.println(bookNames);

		int[] intArray = { 1, 2, 3, 4, 5, 6 };

		Stream<int[]> strArray = Stream.of(intArray);

		IntStream intStr = strArray.flatMapToInt(a -> Arrays.stream(a));

		intStr.forEach(i -> System.out.println(i));

	}
}
