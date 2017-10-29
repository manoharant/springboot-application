package com.sample.mano.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Java8CollectorExample {
	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));

		// Step 1: Stream to List

		List<Person> list = persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());
		list.forEach(p -> System.out.println(p.name));

		// Step 2 : Stream to Map

		Map<Integer, List<Person>> groupByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));
		System.out.println(groupByAge);

		// Step 3 : Steam to Double

		Double averageAge = persons.stream().collect(Collectors.averagingDouble(p -> p.age));

		System.out.println(averageAge);

		// Step 4 : Stream to SummaryStatistics

		IntSummaryStatistics statistics = persons.stream().collect(Collectors.summarizingInt(p -> p.age));
		System.out.println(statistics);

		// Step 5 : Stream to joining

		String names = persons.stream().filter(p -> p.age > 18).map(p -> p.name)
				.collect(Collectors.joining(" and ", "In Germany ", " are eligible to vote"));

		System.out.println(names);

		// Step 6 : Stream to Map
		Map<Integer, String> groupByAge1 = persons.stream()
				.collect(Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> name1 + "," + name2));
		System.out.println(groupByAge1);

		// Step 7 : Collector

		Collector<Person, StringJoiner, String> personCollector = Collector.of(() -> new StringJoiner("#"),
				(j, p) -> j.add(p.name.toUpperCase()), (j1, j2) -> j1.merge(j2), StringJoiner::toString);

		String collectedNames = persons.stream().collect(personCollector);
		System.out.println(collectedNames);

		// Step 8: Custom Collector
		Collector<Person, StringJoiner, String> personNameCollector = Collector.of(() -> {
			System.out.println("supplier");
			return new StringJoiner(" | ");
		}, (j, p) -> {
			System.out.format("accumulator: p=%s; j=%s\n", p, j);
			j.add(p.name.toUpperCase());
		}, (j1, j2) -> {
			System.out.println("merge");
			return j1.merge(j2);
		}, j -> {
			System.out.println("finisher");
			return j.toString();
		});

		String names1 = persons.parallelStream().collect(personNameCollector);

		System.out.println(names1);

	}

}

class Person {
	String name;
	int age;

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}