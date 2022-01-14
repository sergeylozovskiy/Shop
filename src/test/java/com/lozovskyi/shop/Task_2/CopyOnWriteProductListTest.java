package com.lozovskyi.shop.Task_2;

import com.lozovskyi.shop.Task_1.entity.Product;
import com.lozovskyi.shop.Task_1.entity.impl.JewelryRing;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class CopyOnWriteProductListTest {

	private CopyOnWriteProductList<Product> list;
	private CopyOnWriteProductList<Product> extraList;
	private static final int DEFAULT_INDEX = 0;
	private static final int ONE_HUNDRED_CHECK = 100;


	@Before
	public void initializing() {
		list = new CopyOnWriteProductList<>();
		extraList = new CopyOnWriteProductList<>();
	}

	@Test
	public void shouldAddNewElement() {
		list.add(new JewelryRing());
		assertThat(list).hasSize(1);
	}

	@Test
	public void shouldAddNewElementToSpecificIndex() {
		list.add(new JewelryRing());
		JewelryRing toAdd = new JewelryRing();
		list.add(0, toAdd);

		assertThat(list.get(0)).isEqualTo(toAdd);
	}

	@Test
	public void shouldAddAllTheElementsCollection() {
		extraList.add(new JewelryRing());
		extraList.add(new JewelryRing());
		list.addAll(extraList);

		assertThat(list).containsAll(extraList);
	}

	@Test
	public void shouldAddAllTheElementsOfTheIncomeCollectionToIndex() {
		extraList.add(new JewelryRing());
		extraList.add(new JewelryRing());
		JewelryRing toAdd = new JewelryRing();
		list.add(toAdd);
		list.addAll(0, extraList);
		assertThat(list.get(2)).isEqualTo(toAdd);
	}

	@Test
	public void shouldReturnFalseByHasNext() {
		Iterator iterator = list.iterator();
		assertThat(iterator.hasNext()).isFalse();
	}

	@Test
	public void shouldReturnFalseByHasNextAfterAddingNewElementToEmptyList() {
		Iterator iterator = list.iterator();
		list.add(new JewelryRing());

		assertThat(iterator.hasNext()).isFalse();
	}

	@Test
	public void shouldRemoveIncomeObjectFromCollection() {
		JewelryRing toRemove = new JewelryRing();
		list.add(toRemove);
		list.remove(toRemove);
		assertThat(list).isEmpty();
	}

	@Test
	public void shouldReturnTrueByHasNextWhileDynamicAdding() {
		int counter = ONE_HUNDRED_CHECK;
		while (counter > 0) {
			list.add(new JewelryRing());
			counter--;
		}
		Iterator iterator = list.iterator();
		assertThat(iterator.hasNext()).isTrue();
	}

	@Test
	public void shouldNotReturnNewSetElementByNext() {
		JewelryRing toSet = new JewelryRing(3,434,"Fibro","Gold","White",15,925,"round");
		list.add(new JewelryRing());
		list.add(new JewelryRing());
		Iterator iterator = list.iterator();
		list.set(DEFAULT_INDEX, toSet);
		assertThat(iterator.next()).isNotEqualTo(toSet);
	}

	@Test
	public void shouldReturnTrueAfterAddingAnElementAfterClearMethod() {
		list.add(new JewelryRing());
		list.add(new JewelryRing());
		list.clear();
		list.add(new JewelryRing());
		Iterator iterator = list.iterator();
		assertThat(iterator.hasNext()).isTrue();
	}

	@Test
	public void shouldReturnTrueByHasNextAfterRemoveAnObject() {
		list.add(new JewelryRing());
		list.add(new JewelryRing());
		Iterator iterator = list.iterator();
		iterator.next();
		list.remove(0);
		assertThat(iterator.hasNext()).isTrue();
	}

	@Test
	public void shouldReturnTheLastIndexOfTheElement() {
		JewelryRing toAdd = new JewelryRing();
		list.add(toAdd);
		list.add(toAdd);
		assertThat(list.lastIndexOf(toAdd)).isEqualTo(1);
	}

}