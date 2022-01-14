package com.lozovskyi.shop.Task_2;

import com.lozovskyi.shop.Task_1.entity.Product;
import com.lozovskyi.shop.Task_1.Filter;

import java.util.*;

public class CopyOnWriteProductList<T extends Product> implements List<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private Object[] list;
	private int size;
	private boolean flag = true;

	public CopyOnWriteProductList(){
		this.list = new Object[DEFAULT_CAPACITY];
	}

	public CopyOnWriteProductList(int initialCapacity) {
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

	private void updateList() {
		if (flag) {
			list = Arrays.copyOf(list, list.length);
			flag = false;
		}
	}



	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size ==0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >=0;
	}


	@Override
	public Object[] toArray() {
		return Arrays.copyOf(list, size);
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		if (a.length < size) {
			return (T1[]) Arrays.copyOf(list, size, a.getClass());
		}
		System.arraycopy(list, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	@Override
	public boolean add(Product product) {
		updateList();
		sizeCheck(1);
		list[size++] = product;
		return true;
	}

	@Override
	public void add(int index, Product element) {
		updateList();
		indexCheck(index);
		System.arraycopy(list, index, list, index + 1,
				size - index);
		list[index] = element;
		size++;
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		for (T t : collection) {
			if (t != null) {
				add(t);
			}
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		indexCheck(index);
		sizeCheck(index + collection.size());

		for (T t : collection) {
			if (t != null) {
				add(index++, t);
			}
		}
		return false;
	}

	@Override
	public boolean remove(Object object) {
		updateList();
		int index = indexOf(object);
		if (index > -1) {
			remove(index);
			return true;
		}
		return false;
	}

	@Override
	public T remove(int index) {
		updateList();
		indexCheck(index);
		T removed = (T) list[index];
		System.arraycopy(list, index + 1, list, index, size - index - 1);
		list[--size] = null;
		return removed;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		int it = 0;
		while (it < size) {
			if (!collection.contains(list[it])) {
				remove(list[it]);
				if (it > 0) {
					it--;
				}
			} else {
				it++;
			}
		}

		return true;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		Object[] toCheck = collection.toArray();
		for (Object o : toCheck) {
			if (!this.contains(o))
				return false;
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		for (Object e : collection) {
			remove(e);
		}
		return true;
	}

	@Override
	public void clear() {
		updateList();
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
		updateList();
		list[index] = element;
		return (T) list[index];

	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (o != null && o.equals(list[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		for (int i = size - 1; i >= 0; i--) {
			if (o.equals(list[i])) {
				return i;
			}
		}
		return -1;
	}


	@Override
	public ListIterator<T> listIterator() {
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		return new CopyOnWriteProductList.CopyOnWriteProductIterator();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ProductList{").append(System.lineSeparator());
		for (Object o : list) {
			if (o == null) {
				sb.append("null");
			} else {
				sb.append(o.toString());
			}
			sb.append(System.lineSeparator());
		}
		sb.append("}");
		return sb.toString();
	}

	public Iterator<T> iterator(Filter<T> filter) {
		return new CopyOnWriteProductList.CopyOnWriteProductIterator(filter);
	}

	private class CopyOnWriteProductIterator implements Iterator<T> {
		private int cursor;
		private Filter<T> filter;
		private Object[] copy;
		private int copySize;

		CopyOnWriteProductIterator() {
			copy = list;
			copySize = size;
			flag = true;
		}

		CopyOnWriteProductIterator(Filter<T> filter) {
			copy = list;
			copySize = size;
			this.filter = filter;
			flag = true;
		}

		@Override
		public boolean hasNext() {
			if (filter == null) {
				return cursor < copySize;
			}

			for (int i = cursor; i < copySize; i++) {
				if (filter.condition((T) copy[i])) {
					cursor = i;
					return true;
				}
			}
			return false;
		}

		@Override
		public T next() {
			if (hasNext()) {
				return (T) copy[cursor++];
			}
			throw new NoSuchElementException();
		}
	}
}
