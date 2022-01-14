package com.epam.lozovskyi.shop.Task_1;

import com.epam.lozovskyi.shop.Task_1.entity.Product;
import com.epam.lozovskyi.shop.Task_1.entity.impl.JewelryEarrings;
import com.epam.lozovskyi.shop.Task_1.entity.impl.JewelryRing;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ProductListTest {
	private static final int DEFAULT_INDEX = 0;
	private ProductList<Product> list;
	private ProductList<Product> additionalList;

	@Before
	public void initializing() {
		list = new ProductList<>();
		additionalList = new ProductList<>();
	}

	@Test

	public void shouldAddNullWhenCallAddNull() {
		list.add(null);

		assertThat(list).hasSize(1);
	}

	@Test
	public void shouldAddNewElement() {
		list.add(new JewelryEarrings());

		assertThat(list).hasSize(1);
	}

	@Test
	public void shouldAddNewElementOnTheSpecificIndex() {
		list.add(new JewelryEarrings());
		list.add(new JewelryRing());
		list.add(DEFAULT_INDEX, new JewelryRing());
		assertThat(list).hasSize(3);
	}

	@Test
	public void shouldAddAllElementsOfTheCollection() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		additionalList.add(new JewelryEarrings());
		additionalList.add(new JewelryRing());
		list.addAll(additionalList);
		assertThat(list).hasSize(4);
	}

	@Test
	public void shouldAddAllElementOfTheCollectionToTheSpecificIndex() {

		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		list.add(new JewelryRing());
		additionalList.add(new JewelryEarrings());
		additionalList.add(new JewelryRing());
		list.addAll(DEFAULT_INDEX, additionalList);
		assertThat(list).hasSize(5);
	}

	@Test
	public void shouldRemoveAnElementByIndex() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		list.remove(DEFAULT_INDEX);
		assertThat(list).hasSize(1);
	}

	@Test
	public void shouldRemoveAnElement() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		JewelryEarrings removed = new JewelryEarrings();
		list.remove(removed);
		assertThat(list).hasSize(1);
	}

	@Test
	public void shouldRemoveAllTheObjectsThatASpecificCollectionContains() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		additionalList.add(new JewelryEarrings());
		additionalList.add(new JewelryRing());
		list.removeAll(additionalList);
		assertThat(list).hasSize(0);
	}


	@Test
	public void shouldRetainAllObjectsThatAreSpecificCollectionContains() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		list.add(new JewelryRing(5, 5400, "Dark", "gold", "black", 10, 925, "round"));
		additionalList.add(new JewelryRing(5, 5400, "Dark", "gold", "black", 10, 925, "round"));
		list.retainAll(additionalList);
		assertThat(list).hasSize(1);
	}

	@Test
	public void ShouldClear() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		list.clear();
		assertThat(list).hasSize(0);
	}

	@Test
	public void shouldGetTheFirstIndexSpecificObject() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		assertThat(list.indexOf(new JewelryRing())).isEqualTo(DEFAULT_INDEX);
	}

	@Test
	public void shouldGetTheLastIndexOfASpecificObject() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		list.add(new JewelryRing());
		assertThat(list.lastIndexOf(new JewelryRing())).isEqualTo(2);
	}

	@Test
	public void shouldGetObjectBySpecificIndex() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		JewelryRing expProduct = new JewelryRing();
		assertThat(expProduct).isEqualTo(list.get(DEFAULT_INDEX));
	}

	@Test
	public void shouldSetNewObjectOnSpecificIndex() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		JewelryRing setting = new JewelryRing(5, 5400, "Dark", "gold", "black", 10, 925, "round");
		list.set(DEFAULT_INDEX, setting);
		assertThat(setting).isEqualTo(list.get(DEFAULT_INDEX));
	}

	@Test
	public void shouldContainASpecificObject() {
		JewelryRing addNew = new JewelryRing(5, 5400, "Dark", "gold", "black", 10, 925, "round");
		list.add(addNew);
		assertThat(list.contains(addNew)).isTrue();
	}

	@Test
	public void shouldReturnAllTheElementsWithTrueFilter() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		Iterator<Product> iter = list.iterator();
		int counter = 0;
		while (iter.hasNext()) {
			iter.next();
			counter++;
		}
		assertThat(counter).isEqualTo(list.size());
	}

	@Test
	public void shouldReturnFalseWithFalseFilter() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		Iterator<Product> iter = list.iterator(x -> false);
		assertThat(iter.hasNext()).isFalse();
	}

	@Test
	public void shouldThrowNoSuchElementExceptionByIterator() {
		list.add(new JewelryRing());
		list.add(new JewelryEarrings());
		Iterator<Product> iter = list.iterator();
		iter.next();
		iter.next();
		assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(iter::next);
	}
}