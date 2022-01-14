package com.epam.lozovskyi.shop.Task_3.redefinitionHash;

import java.util.Objects;

public class SumOfFourCharsHashMethod {
	private static final int CHARS_NUMBER = 4;

	private String key;

	public SumOfFourCharsHashMethod(String key) {
		this.key = key;
	}

	private int getNameSum() {
		int sum = 0;
		for (int i = 0; i < CHARS_NUMBER; i++) {
			if (i < key.length()) {
				sum += key.charAt(i);
			}
		}
		return sum;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		return getNameSum();
	}

	@Override
	public String toString() {
		return " key: '" + key + '\'';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SumOfFourCharsHashMethod that = (SumOfFourCharsHashMethod) o;
		{
			return Objects.equals(key, that.key);
		}
	}
}
