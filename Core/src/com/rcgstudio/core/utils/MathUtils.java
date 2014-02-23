package com.rcgstudio.core.utils;

import java.util.ArrayList;
import java.util.Random;

public class MathUtils {

	private static Random _random = new Random();

	public static ArrayList<Integer> sortRandomlyNumbers(Integer minValue, Integer numberOfValues) {
		ArrayList<Integer> result = new ArrayList<Integer>(numberOfValues);
		ArrayList<Integer> sortedValues = new ArrayList<Integer>(numberOfValues);
		Random _random = new Random();

		for (int i = minValue; i <= numberOfValues; i++) {
			sortedValues.add(i);
		}

		while (sortedValues.size() != 0) {
			int possitionToInsert = _random.nextInt(sortedValues.size());
			Integer valueToInsert = sortedValues.get(possitionToInsert);
			result.add(valueToInsert);
			sortedValues.remove(possitionToInsert);
		}

		return result;
	}

	public static <T> T drawRandomFromList(ArrayList<T> listToDraw) {
		int randomPosition = _random.nextInt(listToDraw.size());
		T result = listToDraw.get(randomPosition);
		listToDraw.remove(randomPosition);
		return result;
	}

}
