package com.lozovskyi.shop.Task_2;

import com.lozovskyi.shop.Task_1.ProductList;
import com.lozovskyi.shop.Task_1.entity.Product;
import com.lozovskyi.shop.Task_1.entity.impl.JewelryEarrings;
import com.lozovskyi.shop.Task_1.entity.impl.JewelryRing;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ModAndUnmodProductListTest {
	private ModAndUnmodProductList<Product> ModAndUnmodList;
	private ModAndUnmodProductList<Product> empty;
	private ProductList<Product> base;

	@Before
	public void init() {
		base = new ProductList<>();
		base.add(new JewelryRing());
		base.add(new JewelryEarrings());
		ModAndUnmodList = new ModAndUnmodProductList<>(base, new ProductList<>());
	}

	@Test
	public void shouldAddNewElement() {
		JewelryRing toAdd = new JewelryRing();
		ModAndUnmodList.add(toAdd);
		assertThat(ModAndUnmodList).contains(toAdd);
	}
	@Test
	public void shouldThrowIllegalArgumentExceptionByAddingANewElementByIndex() {
		JewelryRing toAdd = new JewelryRing(1,3421,"Hex","Silver","Yellow",12,123,"Butterfly");

		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
			ModAndUnmodList.add(0, toAdd);
		});
	}
	@Test
	public void shouldAddNewElementByIndex() {
		JewelryRing toAdd = new JewelryRing(1,3421,"Hex","Silver","Yellow",12,123,"Butterfly");
		ModAndUnmodList.add(toAdd);
		ModAndUnmodList.add(2, toAdd);
		assertThat(ModAndUnmodList).contains(toAdd);
	}
	@Test
	public void shouldContainASpecificObject() {
		assertThat(ModAndUnmodList.contains(new JewelryRing())).isTrue();
	}

	@Test
	public void shouldNotContainAllTheObjectsFromTheSpecificCollection() {
		assertThat(ModAndUnmodList.containsAll(base)).isTrue();
	}

	@Test
	public void shouldRemoveASpecificElement() {
		JewelryRing toRemove = new JewelryRing(1,3421,"Hex","Silver","Yellow",12,123,"Butterfly");
		ModAndUnmodList.add(toRemove);
		assertThat(ModAndUnmodList.remove(toRemove)).isTrue();
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionByRemovingASpecificElement() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
			ModAndUnmodList.remove(new JewelryRing());
		});
	}

	@Test
	public void shouldReturnTwoElementsByNextMethodOfIterator() {
		Iterator<Product> it = ModAndUnmodList.iterator();
		int counter = 0;
		while (it.hasNext()) {
			it.next();
			counter++;
		}
		assertThat(counter).isEqualTo(2);
	}
	@Test
	public void shouldRemoveAll() {
		ProductList<Product> toRemove = new ProductList<>();
		toRemove.add(new JewelryRing(1,3421,"Hex","Silver","Yellow",12,123,"Butterfly"));
		ModAndUnmodList.removeAll(toRemove);
		assertThat(ModAndUnmodList).hasSize(2);
	}

	@Test
	public void shouldRetainAll() {
		ModAndUnmodList.add(new JewelryRing(1,3421,"Hex","Silver","Yellow",12,123,"Butterfly"));
		ModAndUnmodList.retainAll(base);
		assertThat(ModAndUnmodList).hasSize(2);
	}
	@Test
	public void shouldAddAllTheElementsOnTheSpecificIndex() {
		ModAndUnmodList.add(new JewelryRing());
		ModAndUnmodList.addAll(2, base);
		assertThat(ModAndUnmodList).hasSize(5);
	}
	@Test
	public void shouldSetANewElementByIndex() {
		JewelryRing set = new JewelryRing(1,3421,"Hex","Silver","Yellow",12,123,"Butterfly");
		ModAndUnmodList.add(new JewelryRing());
		ModAndUnmodList.set(2, set);
		assertThat(ModAndUnmodList.get(2)).isEqualTo(set);
	}
}