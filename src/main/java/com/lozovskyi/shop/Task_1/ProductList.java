package com.lozovskyi.shop.Task_1;

import com.lozovskyi.shop.Task_1.entity.Product;

import java.util.*;

public class ProductList<T extends Product> implements List<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private Object[] list;
	private int size;

	public ProductList() {
		this.list = new Object[DEFAULT_CAPACITY];
	}

	public ProductList(int initialCapacity) {
		if (initialCapacity == 0) {
			this.list = new Object[DEFAULT_CAPACITY];
		} else if (initialCapacity > 0) {
			this.list = new Object[initialCapacity];
		} else {
			throw new IllegalArgumentException("Illegal Capacity: " +
					initialCapacity);
		}
	}

	private void ensureCapacity(int capacity) {
		int newCapacity = (capacity + 1) * 3 / 2;
		list = Arrays.copyOf(list, newCapacity);
	}

	private boolean isIndexValid(int toCheck) {
		return toCheck >= 0 && toCheck < size;
	}

	private boolean isEnough(int toAddSize) {
		return (size + toAddSize) < this.list.length;
	}

	private void indexCheck(int index) {
		if (!isIndexValid(index)) {
			throw new IndexOutOfBoundsException();
		}
	}

	private void sizeCheck(int capacity) {
		if (!isEnough(capacity)) {
			ensureCapacity(size);
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override

	public boolean contains(Object object) {
		return indexOf(object) >= 0;
	}


	@Override
	public Object[] toArray() {
		return Arrays.copyOf(list, size);
	}

	@Override
	public <T1> T1[] toArray(T1[] array) {
		if (array.length < size) {
			return (T1[]) Arrays.copyOf(list, size, array.getClass());
		}
		System.arraycopy(list, 0, array, 0, size);
		if (array.length > size) {
			array[size] = null;
		}
		return array;
	}

	@Override
	public boolean add(Product product) {
		sizeCheck(1);
		list[size++] = product;
		return true;
	}

	@Override
	public void add(int index, Product element) {
		indexCheck(index);
		System.arraycopy(list, index, list, index + 1,
				size - index);
		list[index] = element;
		size++;

	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		for (T type : collection) {
			if (type != null) {
				add(type);
			}
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		indexCheck(index);
		sizeCheck(index + collection.size());

		for (T type : collection) {
			if (type != null) {
				add(index++, type);
			}
		}
		return false;
	}

	@Override
	public boolean remove(Object object) {
		int index = indexOf(object);
		if (index > -1) {
			remove(index);
			return true;
		}
		return false;
	}

	@Override
	public T remove(int index) {
		indexCheck(index);
		T removed = (T) list[index];
		System.arraycopy(list, index + 1, list, index, size - index - 1);
		list[--size] = null;
		return removed;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		for (Object object : collection) {
			remove(object);
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		int tmp = 0;
		while (tmp < size) {
			if (!collection.contains(list[tmp])) {
				remove(list[tmp]);
				if (tmp > 0) {
					tmp--;
				}
			} else {
				tmp++;
			}
		}

		return true;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		Object[] toCheck = collection.toArray();
		for (Object object : toCheck) {
			if (!this.contains(object))
				return false;
		}
		return true;
	}

	@Override
	public void clear() {
		list = new Object[list.length];
		size = 0;
	}

	@Override
	public T get(int index) {
		indexCheck(index);
		return (T) list[index];
	}

	@Override
	public T set(int index, Product element) {
		list[index] = element;
		return (T) list[index];
	}

	@Override
	public int indexOf(Object object) {
		for (int i = 0; i < size; i++) {
			if (object != null && object.equals(list[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object object) {
		for (int i = size - 1; i >= 0; i--) {
			if (object.equals(list[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ProductList{").append(System.lineSeparator());
		for (Object o : list) {
			if (o == null) {
				stringBuilder.append("null");
			} else {
				stringBuilder.append(o.toString());
			}
			stringBuilder.append(System.lineSeparator());
		}
		stringBuilder.append("}");
		return stringBuilder.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new ProductIterator();
	}

	public Iterator<T> iterator(Filter<T> filter) {
		return new ProductIterator(filter);
	}

	private class ProductIterator implements Iterator<T> {
		private int current;
		private Filter<T> filter;

		ProductIterator() {
		}

		ProductIterator(Filter<T> filter) {
			this.filter = filter;
		}

		@Override
		public boolean hasNext() {
			if (filter == null) {
				return current < size;
			}

			for (int i = current; i < size; i++) {
				if (filter.condition((T) list[i])) {
					current = i;
					return true;
				}
			}
			return false;
		}

		@Override
		public T next() {
			if (hasNext()) {
				return (T) list[current++];
			}
			throw new NoSuchElementException();
		}
	}

}
