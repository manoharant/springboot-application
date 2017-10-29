package com.mano.demo.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Java8ObjectStream {

	static class Person {
		String name;
		int age;

		Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));

		personStream(persons);
	}

	private static void personStream(List<Person> persons) {
		// Method : toList
		List<Person> filtered = persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());
		System.out.println(filtered);
		// Method : groupingBy
		Map<Integer, List<Person>> personByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));
		personByAge.forEach((age, p) -> System.out.format("age %s:%s\n", age, p));
		// Method : averagingInt
		Double averageAge = persons.stream().collect(Collectors.averagingInt(a -> a.age));
		System.out.println("Average Age:" + averageAge);
		// Method:SummarizingInt
		IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(a -> a.age));

		System.out.println("Age Summary::" + ageSummary);
		// Method : joining
		String names = persons.stream().filter(p -> p.age >= 23).map(p -> p.name)
				.collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
		System.out.println(names);
		// Method : toMap
		Map<Integer, String> nameMap = persons.stream()
				.collect(Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> name1 + ":" + name2));

		System.out.println(nameMap);

		// Method : personCollector

		Collector<Person, StringJoiner, String> personCollector = Collector.of(() -> new StringJoiner("|"),
				(j, p) -> j.add(p.name.toUpperCase()), (j1, j2) -> j1.merge(j2), StringJoiner::toString);

		String collectedNames = persons.stream().collect(personCollector);

		System.out.println(collectedNames);

		// Method : personCollector

		Collector<Person, StringJoiner, String> personCollector1 = Collector.of(() -> {
			System.out.println("supplier");
			return new StringJoiner("|");
		}, (j, p) -> {
			System.out.format("acumulator j=%s p=%s\n", j, p);
			j.add(p.name.toUpperCase());
		}, (j1, j2) -> {
			System.out.println("merge");
			return j1.merge(j2);
		}, j -> {
			System.out.println("finisher");
			return j.toString();
		});

		String collectedNames1 = persons.stream().collect(personCollector1);
		System.out.println(collectedNames1);

	}

}
