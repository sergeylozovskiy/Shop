package com.epam.lozovskyi.shop.Task_3;

import com.epam.lozovskyi.shop.Task_3.redefinitionHash.LengthHashMethod;
import org.junit.Before;
import org.junit.Test;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LengthHashMethodTest {

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
		LengthHashMethod one = new LengthHashMethod("first");
		LengthHashMethod two = new LengthHashMethod("second");
		hashMap.put(one.hashCode(), one);
		hashMap.put(two.hashCode(), two);
		assertThat(hashMap).hasSize(2);
	}

	@Test
	public void addTwoDifferentElementsInLinkedHashMap() {
		LengthHashMethod one = new LengthHashMethod("first");
		LengthHashMethod two = new LengthHashMethod("second");
		linkedMap.put(one.hashCode(), one);
		linkedMap.put(two.hashCode(), two);
		assertThat(linkedMap).hasSize(2);
	}

	@Test
	public void addTwoDifferentElementsInHashTable() {
		LengthHashMethod one = new LengthHashMethod("first");
		LengthHashMethod two = new LengthHashMethod("second");
		table.put(one.hashCode(), one);
		table.put(two.hashCode(), two);
		assertThat(table).hasSize(2);
	}
	@Test
	public void addTwoSameElementsInHashMap() {
		LengthHashMethod one = new LengthHashMethod("first");
		LengthHashMethod two = new LengthHashMethod("first");
		hashMap.put(one.hashCode(), one);
		hashMap.put(two.hashCode(), two);
		assertThat(hashMap).hasSize(1);
	}

	@Test
	public void addTwoSameElementsInLinkedHashMap() {
		LengthHashMethod one = new LengthHashMethod("first");
		LengthHashMethod two = new LengthHashMethod("first");
		linkedMap.put(one.hashCode(), one);
		linkedMap.put(two.hashCode(), two);
		assertThat(linkedMap).hasSize(1);
	}

	@Test
	public void addTwoSameElementsInHashTable() {
		LengthHashMethod one = new LengthHashMethod("first");
		LengthHashMethod two = new LengthHashMethod("first");
		table.put(one.hashCode(), one);
		table.put(two.hashCode(), two);
		assertThat(table).hasSize(1);
	}

	@Test
	public void shouldIterate() {
		Enumeration names;
		Object str;
		int i = 0;
		LengthHashMethod one = new LengthHashMethod("first");
		LengthHashMethod two = new LengthHashMethod("second");
		LengthHashMethod three = new LengthHashMethod("thirdsq");
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