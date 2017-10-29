package com.sample.mano.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Java8StreamExample {

	public static void main(String[] args) {

		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		// Step 1: filtering

		stringCollection.stream().filter(a -> a.startsWith("a")).forEach(System.out::println);

		// Step 2: sorting

		stringCollection.stream().sorted().filter(a -> a.startsWith("a")).forEach(System.out::println);

		// Step 3: mapping

		stringCollection.stream().sorted().filter(a -> a.startsWith("a")).map(a -> a.toUpperCase())
				.forEach(System.out::println);

		// Step 4 : matching

		boolean anyStartsWithA = stringCollection.stream().anyMatch(a -> a.startsWith("a"));
		System.out.println("anyStartsWithA ::" + anyStartsWithA);
		boolean allStartsWithA = stringCollection.stream().allMatch(a -> a.startsWith("a"));
		System.out.println("allStartsWithA::" + allStartsWithA);
		boolean noneStartsWithZ = stringCollection.stream().noneMatch(a -> a.startsWith("z"));
		System.out.println("noneStartsWithZ::" + noneStartsWithZ);

		// Step 5 : counting

		long count = stringCollection.stream().filter(a -> a.startsWith("a")).count();
		System.out.println("Count ::" + count);

		// Step 6 : reducing

		Optional<String> reduce = stringCollection.stream().sorted().reduce((a, b) -> a + "#" + b);
		reduce.ifPresent(System.out::println);

	}

}
