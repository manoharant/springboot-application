package com.mano.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Java8ForEachExample {

	public static void main(String[] args) {
		List<Integer> myList = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			myList.add(i);
		// Java 7 approach
		Iterator<Integer> itr = myList.iterator();
		while (itr.hasNext()) {
			System.out.println("Java 7 approach :: " + itr.next());
		}
		// Java 8 forEach consumer approach
		myList.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				System.out.println("Java 8 forEach consumer approach :: " + t);
			}
		});

		myList.forEach(new MyConsumer());

		// Java 8 Lamda expression

		myList.forEach((arg) -> System.out.println("Java 8 Lamda expression :: " + arg));

		// Java 8 Method reference

		myList.forEach((System.out::println));

	}

}

class MyConsumer implements Consumer<Integer> {

	@Override
	public void accept(Integer t) {
		System.out.println("Java 8 forEach consumer implementation :: " + t);
	}

}