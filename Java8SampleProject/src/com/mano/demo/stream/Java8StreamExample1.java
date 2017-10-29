package com.mano.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Java8StreamExample1 {

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

		// filtering
		stringCollection.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
		// sorting
		stringCollection.stream().sorted().filter(s -> s.startsWith("a")).forEach(System.out::println);
		// mapping
		stringCollection.stream().map(s -> s.toUpperCase()).sorted((a, b) -> a.compareTo(b))
				.filter(s -> s.startsWith("A")).forEach(System.out::println);

		// matching
		boolean anyStartsWitA = stringCollection.stream().anyMatch(s -> s.startsWith("a"));
		System.out.println(anyStartsWitA);

		boolean allMatchA = stringCollection.stream().allMatch(s -> s.startsWith("a"));
		System.out.println(allMatchA);

		boolean noneMatch = stringCollection.stream().noneMatch(s -> s.startsWith("z"));
		System.out.println(noneMatch);

		// counting

		long startsWithB = stringCollection.stream().filter(s -> s.startsWith("b")).count();
		System.out.println(startsWithB);

		// reducing

		Optional<String> reduce = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "::" + s2);
		reduce.ifPresent(System.out::println);
		
		System.out.println(stringCollection);
	}

}
