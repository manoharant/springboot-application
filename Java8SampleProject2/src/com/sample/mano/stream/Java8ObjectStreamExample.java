package com.sample.mano.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Java8ObjectStreamExample {

	public static void main(String[] args) {
		List<Foo> foos = new ArrayList<>();

		IntStream.range(0, 5).forEach(num -> foos.add(new Foo("Foo" + num)));

		foos.forEach(foo -> IntStream.range(0, 2).forEach(num -> foo.bars.add(new Bar("Bar" + num))));

		foos.stream().flatMap(foo -> foo.bars.stream()).forEach(f -> System.out.println(f.name));

	}

}

class Foo {
	String name;
	List<Bar> bars = new ArrayList<>();

	Foo(String name) {
		this.name = name;
	}
}

class Bar {
	String name;

	Bar(String name) {
		this.name = name;
	}
}
