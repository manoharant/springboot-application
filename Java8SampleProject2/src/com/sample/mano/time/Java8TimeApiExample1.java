package com.sample.mano.time;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;

public class Java8TimeApiExample1 {
	public static void main(String[] args) {
		Java8TimeApiExample1 java8tester = new Java8TimeApiExample1();
		java8tester.testLocalDateTime();
	}

	public void testLocalDateTime() {
		LocalDateTime silvester = LocalDateTime.of(2017, Month.DECEMBER, 31, 23, 59, 59);

		DayOfWeek dayOfWeek = silvester.getDayOfWeek();
		Month month = silvester.getMonth();
		System.out.println(dayOfWeek);
		System.out.println(month);
	}
}
