package com.hub4u.training;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<Integer> ints = Arrays.asList(2, 4, 6, 8);
		Integer sum = ints.stream().reduce(10, (a, b) -> a-b );
		
		System.out.println(sum);

	}

}
