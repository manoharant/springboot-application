package com.sample.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8MapSample {
	public static void main(String[] args) {
		List<String> alpha = Arrays.asList("a", "b", "c", "d");

		List<String> upperCase = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(upperCase);

		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		List<Integer> squereNum = nums.stream().map(n -> n * n).collect(Collectors.toList());
		System.out.println(squereNum);

		List<Staff> staff = Arrays.asList(new Staff("mkyong", 30, new BigDecimal(10000)),
				new Staff("jack", 27, new BigDecimal(20000)), new Staff("lawrence", 33, new BigDecimal(30000)));

		List<String> names = staff.stream().map(Staff::getName).collect(Collectors.toList());
		System.out.println(names);
		
	}
}
