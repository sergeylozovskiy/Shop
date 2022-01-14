package com.epam.lozovskyi.shop.Task_3;

import com.epam.lozovskyi.shop.Task_1.entity.Product;
import com.epam.lozovskyi.shop.Task_3.exceptions.UniqueElementException;

import java.util.*;

public class UniqueProductList<T extends Product> extends ArrayList<T> {
	//private static final String UNSUPPORTED_OPERATION = "Using of this method is not allowed!";
	private static final String INDEX_OUT_OF_BOUNDS = "Income index is not valid!";
	private static final String NOT_UNIQUE_ELEMENT = "Element isn't unique!";

	@Override
	public boolean add(T toAdd) {
		checkUnique(toAdd);
		return super.add(toAdd);
	}

	@Override
	public void add(int index, T toAdd) {
		checkIndex(index);
		checkUnique(toAdd);
		super.add(index, toAdd);
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		checkUnique(collection);
		return super.addAll(collection);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		checkIndex(index);
		checkUnique(collection);
		return super.addAll(index, collection);
	}

	@Override
	public T set(int index, T toSet) {
		checkIndex(index);
		checkUniqueToSet(index, toSet);
		return super.set(index, toSet);
	}

	private void checkIndex(int toCheck) {
		if (toCheck < 0 || toCheck >= size()) {
			throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS);
		}
	}

	private void checkUniqueToSet(int index, T toCheck) {
		if (contains(toCheck)) {
			if (!Objects.equals(get(index), toCheck)) {
				throw new UniqueElementException(NOT_UNIQUE_ELEMENT);
			}
		}
	}

	private void checkUnique(T toCheck) {
		if (contains(toCheck)) {
			throw new UniqueElementException(NOT_UNIQUE_ELEMENT);
		}
	}

	private void checkUnique(Collection<? extends T> toCheck) {
		checkForUniqueContent(toCheck);
		for (T product : toCheck) {
			checkUnique(product);
		}
	}

	private void checkForUniqueContent(Collection<? extends T> toCheck) {
		Set<T> set = new HashSet<>(toCheck);
		if (set.size() != toCheck.size()) {
			throw new UniqueElementException(NOT_UNIQUE_ELEMENT);
		}
	}

}
