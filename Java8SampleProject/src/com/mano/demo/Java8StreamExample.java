package com.mano.demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Java8StreamExample {
	public static void main(String[] args) {

		// Java 7 way to calculate sum
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		int sum = sumJava7Iterator(list);
		System.out.println("JAVA 7 Sum::" + sum);

		// Java 8 way to calculate sum
		int sumJava8Stream = sumJava8StreamApproach(list);
		System.out.println("JAVA 8 Stream Sum::" + sumJava8Stream);

		int sumJava8ParallemStream = sumJava8StreamApproach(list);
		System.out.println("JAVA 8 Parallel Stream Sum::" + sumJava8ParallemStream);
	}

	public static int sumJava7Iterator(List<Integer> list) {
		Iterator<Integer> itr = list.iterator();
		int sum = 0;

		while (itr.hasNext()) {
			int num = itr.next();
			if (num > 5)
				sum = sum + num;
		}
		return sum;
	}

	public static int sumJava8StreamApproach(List<Integer> list) {
		return list.stream().filter(n -> n > 5).mapToInt(i -> i).sum();
	}

	public static int sumJava8ParallelStreamApproach(List<Integer> list) {
		return list.parallelStream().filter(n -> n > 5).mapToInt(i -> i).sum();
	}
}
