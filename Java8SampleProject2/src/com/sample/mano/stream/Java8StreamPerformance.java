package com.sample.mano.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Java8StreamPerformance {
	public static final long MAX = 10000000L;

	public static void main(String[] args) {
		testStream();
		testParallelStream();
	}

	static void testStream() {
		List<String> values = new ArrayList<>();
		for (long i = 0; i < MAX; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}

		long t0 = System.nanoTime();

		long valCount = values.stream().sorted().count();
		System.out.println("valCount::" + valCount);

		long t1 = System.nanoTime();

		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);

		System.out.format("time taken in %d ms", millis);
	}

	static void testParallelStream() {
		List<String> values = new ArrayList<>();
		for (long i = 0; i < MAX; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}

		long t0 = System.nanoTime();

		long valCount = values.parallelStream().sorted().count();

		System.out.println("valCount::" + valCount);

		long t1 = System.nanoTime();

		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.format("time taken in %d ms", millis);
	}

}
