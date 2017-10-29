package com.sample.mano.stream;

import java.util.Arrays;
import java.util.List;

public class Java8ReduceExample {
	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));
		// Step 1:
		persons.stream().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2).ifPresent(System.out::println);

		// Step 2:
		Person result = persons.stream().reduce(new Person("", 0), (p1, p2) -> {
			p1.age += p2.age;
			p1.name += p2.name;
			return p1;
		});
		System.out.format("Age : %d , name:%s\n", result.age, result.name);

		// Step 3:

		Integer ageSum = persons.stream().reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
		System.out.println(ageSum);

		Integer ageSum1 = persons.parallelStream().reduce(0, (sum, p) -> {
			System.out.format("accumulator: sum=%s; person=%s; thread=%s\n", sum, p, Thread.currentThread().getName());
			return sum += p.age;
		}, (sum1, sum2) -> {
			System.out.format("combiner: sum1=%s; sum2=%s; thread=%s\n", sum1, sum2, Thread.currentThread().getName());
			return sum1 + sum2;
		});

		System.out.println(ageSum1);
	}
}
