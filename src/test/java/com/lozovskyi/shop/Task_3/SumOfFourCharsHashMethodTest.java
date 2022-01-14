package com.lozovskyi.shop.Task_3;

import com.lozovskyi.shop.Task_3.redefinitionHash.SumOfFourCharsHashMethod;
import org.junit.Before;
import org.junit.Test;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class SumOfFourCharsHashMethodTest {
	private HashMap<Integer, Object> hashMap;
	private LinkedHashMap<Integer, Object> linkedMap;
	private Hashtable<Integer, Object> table;

	@Before
	public void initialization() {
		hashMap = new HashMap<>();
		linkedMap = new LinkedHashMap<>();
		table = new Hashtable<>();
	}

	@Test
	public void addTwoDifferentElementsInHashMap() {
		SumOfFourCharsHashMethod one = new SumOfFourCharsHashMethod("first");
		SumOfFourCharsHashMethod two = new SumOfFourCharsHashMethod("second");
		hashMap.put(one.hashCode(), one);
		hashMap.put(two.hashCode(), two);

		assertThat(hashMap).hasSize(2);
	}

	@Test
	public void addTwoDifferentElementsInLinkedHashMap() {
		SumOfFourCharsHashMethod one = new SumOfFourCharsHashMethod("first");
		SumOfFourCharsHashMethod two = new SumOfFourCharsHashMethod("second");
		linkedMap.put(one.hashCode(), one);
		linkedMap.put(two.hashCode(), two);
		assertThat(linkedMap).hasSize(2);
	}

	@Test
	public void addTwoDifferentElementsInHashTable() {
		SumOfFourCharsHashMethod one = new SumOfFourCharsHashMethod("first");
		SumOfFourCharsHashMethod two = new SumOfFourCharsHashMethod("second");
		table.put(one.hashCode(), one);
		table.put(two.hashCode(), two);
		assertThat(table).hasSize(2);
	}

	@Test
	public void addTwoSameElementsInHashMap() {
		SumOfFourCharsHashMethod one = new SumOfFourCharsHashMethod("first");
		SumOfFourCharsHashMethod two = new SumOfFourCharsHashMethod("first");
		hashMap.put(one.hashCode(), one);
		hashMap.put(two.hashCode(), two);
		assertThat(hashMap).hasSize(1);
	}

	@Test
	public void addTwoSameElementsInLinkedHashMap() {
		SumOfFourCharsHashMethod one = new SumOfFourCharsHashMethod("first");
		SumOfFourCharsHashMethod two = new SumOfFourCharsHashMethod("first");
		linkedMap.put(one.hashCode(), one);
		linkedMap.put(two.hashCode(), two);
		assertThat(linkedMap).hasSize(1);
	}

	@Test
	public void addTwoSameElementsInHashTable() {
		SumOfFourCharsHashMethod one = new SumOfFourCharsHashMethod("first");
		SumOfFourCharsHashMethod two = new SumOfFourCharsHashMethod("first");
		table.put(one.hashCode(), one);
		table.put(two.hashCode(), two);
		assertThat(table).hasSize(1);
	}
	@Test
	public void shouldIterate() {
		Enumeration names;
		Object str;
		int i = 0;
		SumOfFourCharsHashMethod one = new SumOfFourCharsHashMethod("first");
		SumOfFourCharsHashMethod two = new SumOfFourCharsHashMethod("second");
		SumOfFourCharsHashMethod three = new SumOfFourCharsHashMethod("third");

		table.put(one.hashCode(),one);
		table.put(two.hashCode(),two);
		table.put(three.hashCode(),three);
		names = table.keys();

		while(names.hasMoreElements()) {
			str = names.nextElement();
			System.out.println(str + ":" + table.get(str));
			System.out.println("----------");
			i++;
		}
		assertThat(i).isEqualTo(3);
	}
}
