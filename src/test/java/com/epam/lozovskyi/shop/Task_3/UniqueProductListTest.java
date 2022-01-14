package com.epam.lozovskyi.shop.Task_3;

import com.epam.lozovskyi.shop.Task_1.entity.Product;
import com.epam.lozovskyi.shop.Task_1.entity.impl.JewelryEarrings;
import com.epam.lozovskyi.shop.Task_1.entity.impl.JewelryRing;
import com.epam.lozovskyi.shop.Task_3.exceptions.UniqueElementException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class UniqueProductListTest {
	private static List<Product> test;
	private static List<Product> income;
	private static Product first;
	private static Product second;
	private static Product third;

	@BeforeClass
	public static void create() {
		first = new JewelryRing();
		second = new JewelryEarrings();
		third = new JewelryRing(3,434,"Fibro","Gold","White",15,925,"round");

	}

	@Before
	public void init() {
		income = new ArrayList<>();
		income.add(first);
		income.add(second);
		test = new UniqueProductList<>();
	}

	@Test
	public void shouldAddOneElementByAdd() {
		test.add(first);
		assertThat(test).contains(first);
	}

	@Test
	public void shouldAddTwoElementsByAdd() {
		test.add(first);
		test.add(second);
		assertThat(test).contains(first, second);
	}

	@Test
	public void shouldAddOneElementByAddWithIncomeIndex() {
		test.add(first);
		test.add(0, second);
		assertThat(test).startsWith(second);
	}

	@Test
	public void shouldThrowUniqueElementExceptionByAdd() {
		test.add(first);
		assertThatExceptionOfType(UniqueElementException.class).isThrownBy(() -> {
			test.add(first);
		});
	}

	@Test
	public void shouldThrowUniqueElementExceptionByAddWithIncomeIndex() {
		test.add(first);
		assertThatExceptionOfType(UniqueElementException.class).isThrownBy(() -> {
			test.add(0, first);
		});
	}

	@Test
	public void shouldAddAllTheElementsOfTheIncomeCollection() {
		test.addAll(income);
		assertThat(test).containsAll(income);
	}

	@Test
	public void shouldThrowUniqueElementExceptionByAddAll() {
		test.addAll(income);
		assertThatExceptionOfType(UniqueElementException.class).isThrownBy(() -> {
			test.addAll(income);
		});
	}

	@Test
	public void shouldAddAllTheElementsOfTheIncomeCollectionByIndex() {
		test.add(third);
		test.addAll(0, income);
		assertThat(test).containsAll(income);
	}

	@Test
	public void shouldThrowUniqueElementExceptionByAddAllByIndex() {
		test.add(first);
		assertThatExceptionOfType(UniqueElementException.class).isThrownBy(() -> {
			test.addAll(0, income);
		});
	}

	@Test
	public void shouldSetTheIncomeElementToASpecificIndex() {
		test.addAll(income);
		test.set(0, third);
		assertThat(test.get(0)).isEqualTo(third);
	}

	@Test
	public void shouldReplaceAll() {
		test.add(first);
		test.replaceAll(product -> second);
		assertThat(test.get(0)).isEqualTo(second);
	}
}