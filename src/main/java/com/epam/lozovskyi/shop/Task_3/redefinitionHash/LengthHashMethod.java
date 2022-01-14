package com.epam.lozovskyi.shop.Task_3.redefinitionHash;

import java.util.Objects;

public class LengthHashMethod {
	private String key;

	public LengthHashMethod(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		return key.length();
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
		LengthHashMethod that = (LengthHashMethod) o;
		{
			return Objects.equals(key, that.key);
		}
	}
}