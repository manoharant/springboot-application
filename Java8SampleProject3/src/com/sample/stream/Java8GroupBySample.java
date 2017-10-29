package com.sample.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8GroupBySample {
	public static void main(String[] args) {
		// 3 apple, 2 banana, others 1
		List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

		Map<String, Long> groupByCount = items.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(groupByCount);

		Map<String, Long> sortedGroupbyMap = groupByCount.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o2, LinkedHashMap::new));
		System.out.println(sortedGroupbyMap);

		List<Item> itemsObj = Arrays.asList(new Item("apple", 10, new BigDecimal("9.99")),
				new Item("banana", 20, new BigDecimal("19.99")), new Item("orang", 10, new BigDecimal("29.99")),
				new Item("watermelon", 10, new BigDecimal("29.99")), new Item("papaya", 20, new BigDecimal("9.99")),
				new Item("apple", 10, new BigDecimal("9.99")), new Item("banana", 10, new BigDecimal("19.99")),
				new Item("apple", 20, new BigDecimal("9.99")));

		Map<String, Long> groupItemsObjByname = itemsObj.stream()
				.collect(Collectors.groupingBy(Item::getName, Collectors.counting()));
		System.out.println(groupItemsObjByname);

		Map<String, Integer> groupByQuantity = itemsObj.stream()
				.collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
		System.out.println(groupByQuantity);

		Map<BigDecimal, List<Item>> groupByPrice = itemsObj.stream().collect(Collectors.groupingBy(Item::getPrice));
		System.out.println(groupByPrice);

		Map<BigDecimal, Set<String>> groupByPriceSet = itemsObj.stream()
				.collect(Collectors.groupingBy(Item::getPrice, Collectors.mapping(Item::getName, Collectors.toSet())));
		System.out.println(groupByPriceSet);
	}

}