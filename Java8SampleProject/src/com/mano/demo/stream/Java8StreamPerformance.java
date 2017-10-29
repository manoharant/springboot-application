package com.mano.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Java8StreamPerformance {
	public static final long MAX = 10000000L;

	public static void main(String[] args) {
		// sortSerialStream();
		sortParallelStream();
	}

	public static void sortSerialStream() {
		List<String> values = new ArrayList<>();
		for (long i = 0; i < MAX; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}

		// sequestial stream
		long t0 = System.nanoTime();
		long count = values.stream().sorted().count();
		System.out.println("sequential count:" + count);
		long t1 = System.nanoTime();

		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took %d ms", millis));
	}

	public static void sortParallelStream() {
		List<String> values = new ArrayList<>();
		for (long l = 0; l < MAX; l++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}

		long t0 = System.nanoTime();
		long count = values.parallelStream().sorted().count();
		System.out.println("parallel count:" + count);
		long t1 = System.nanoTime();

		long duration = TimeUnit.NANOSECONDS.toMillis(t1 - t0);

		System.out.println(String.format("parallel sort took %d ms", duration));
	}
}
