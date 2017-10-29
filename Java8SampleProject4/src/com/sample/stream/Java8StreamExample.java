package com.sample.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

		List<String> filterStr = stringCollection.stream().filter(s -> s.startsWith("a")).collect(Collectors.toList());
		System.out.println("Filtered list :: " + filterStr);

		// Step 2 : sorting
		List<String> sortStr = stringCollection.stream().sorted((s1, s2) -> s1.compareTo(s2))
				.collect(Collectors.toList());
		System.out.println("Sorted list ::" + sortStr);

		// Step 3: Mapping

		List<String> mapStr = stringCollection.stream().sorted().map((s) -> s.toUpperCase())
				.collect(Collectors.toList());
		System.out.println("Mapping list ::" + mapStr);

		// Step 4: Matching

		boolean anyMatch = stringCollection.stream().anyMatch(p -> p.startsWith("a"));
		boolean allMatch = stringCollection.stream().allMatch(p -> p.startsWith("a"));
		boolean noneMatch = stringCollection.stream().noneMatch(p -> p.startsWith("z"));

		System.out.println("Matching :: [" + anyMatch + "," + allMatch + "," + noneMatch + "]");

		// Step 4 : Count

		long count = stringCollection.stream().count();
		System.out.println("Counting :: " + count);

		// Step 5 : Reducing

		Optional<String> reduceStr = stringCollection.stream().map(s -> s.toUpperCase()).sorted()
				.reduce((s1, s2) -> s1 + "#" + s2);
		reduceStr.ifPresent(str -> System.out.println("Reducing ::" + str));

		// Step 6 : IntStream

		IntStream.range(0, 10).filter(i -> i % 2 == 0).forEach(System.out::println);

		OptionalInt optionalSum = IntStream.range(0, 5).map(n -> n * n).reduce((n1, n2) -> n1 + n2);
		optionalSum.ifPresent(System.out::println);

		int sum = IntStream.range(0, 5).reduce(0, (n1, n2) -> n1 + n2);
		System.out.println(sum);

	}

}
