package com.epam.lozovskyi.shop.Task_2;

import com.epam.lozovskyi.shop.Task_1.entity.Product;

import java.util.*;

public class ModAndUnmodProductList <T extends Product> implements List<T> {
	private static final String EXCEPTION_MESSAGE_FOR_UNMODIFYING_PART = "Unmodifiable is not allowed!";
	private static final String EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS = "Out of bounds!";

	private List<T> readAndWriteList;
	private List<T> readOnlyList;

	public ModAndUnmodProductList(List<T> forReadOnly, List<T> readAndWrite) {
		readOnlyList = forReadOnly;
		readAndWriteList = readAndWrite;
	}

	private boolean containsAny(Collection<?> collection) {
		for (Object element : collection) {
			if (readOnlyList.contains(element)) {
				return true;
			}
		}
		return false;
	}

	private boolean containsMoreThan(Collection<?> collection) {
		for (T element : readOnlyList) {
			if (!collection.contains(element)) {
				return true;
			}
		}
		return false;
	}

	private int getModifiableListStartIndex(int index) {
		return index - readOnlyList.size();
	}

	private void checkForIndexOutOfBoundsException(int index) {
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException(EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS);
		}
	}

	private void checkForIllegalArgumentException(int index) {
		if (index < readOnlyList.size()) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE_FOR_UNMODIFYING_PART);
		}
	}

	@Override
	public int size() {
		return readOnlyList.size() + readAndWriteList.size();
	}

	@Override
	public boolean isEmpty() {
		return readOnlyList.isEmpty() && readAndWriteList.isEmpty();

	}

	@Override
	public boolean contains(Object o) {
		return readOnlyList.contains(o) || readAndWriteList.contains(o);
	}


	@Override
	public Object[] toArray() {
		Object[] sumArray = readOnlyList.toArray();
		System.arraycopy(readAndWriteList.toArray(), 0, sumArray, sumArray.length - 1,
				readAndWriteList.size());
		return sumArray;
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		if (a.length < size()) {
			return (T1[]) Arrays.copyOf(toArray(), size(), a.getClass());
		}
		System.arraycopy(toArray(), 0, a, 0, size());
		if (a.length > size()) {
			a[size()] = null;
		}
		return a;
	}

	@Override
	public boolean add(T t) {
		return readAndWriteList.add(t);
	}

	@Override
	public boolean remove(Object o) {
		if (readOnlyList.contains(o)) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE_FOR_UNMODIFYING_PART);
		}
		return readAndWriteList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		if(isEmpty()) {
			return false;
		}
		for (Object object : collection) {
			if (!contains(object)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return readAndWriteList.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (containsAny(c)) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE_FOR_UNMODIFYING_PART);
		}
		return readAndWriteList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (containsMoreThan(c)) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE_FOR_UNMODIFYING_PART);
		}
		return readAndWriteList.retainAll(c);
	}

	@Override
	public void clear() {
		if (readOnlyList.isEmpty()) {
			readAndWriteList.clear();
		} else {
			throw new IllegalStateException(EXCEPTION_MESSAGE_FOR_UNMODIFYING_PART);
		}
	}

	@Override
	public T get(int index) {
		checkForIndexOutOfBoundsException(index);
		if (index < readOnlyList.size()) {
			return readOnlyList.get(index);
		}
		return readAndWriteList.get(getModifiableListStartIndex(index));
	}

	@Override
	public T set(int index, T element) {
		checkForIndexOutOfBoundsException(index);
		checkForIllegalArgumentException(index);
		return readAndWriteList.set(getModifiableListStartIndex(index), element);
	}

	@Override
	public void add(int index, T element) {
		checkForIndexOutOfBoundsException(index);
		checkForIllegalArgumentException(index);
		readAndWriteList.add(getModifiableListStartIndex(index), element);
	}

	@Override
	public T remove(int index) {
		checkForIndexOutOfBoundsException(index);
		checkForIllegalArgumentException(index);
		return readAndWriteList.remove(getModifiableListStartIndex(index));
	}

	@Override
	public int indexOf(Object o) {
		int index = readOnlyList.indexOf(o);
		if (index < 0) {
			return readAndWriteList.indexOf(o) + readOnlyList.size();
		}
		return index;
	}

	@Override
	public int lastIndexOf(Object o) {
		int index = readAndWriteList.lastIndexOf(o);
		if (index < 0) {
			return readOnlyList.lastIndexOf(o);
		}
		return index + readOnlyList.size();
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
	public Iterator<T> iterator() {
		return new TwoInOneIterator();
	}

	private class TwoInOneIterator implements Iterator<T> {
		private int cursor = 0;

		public TwoInOneIterator() {
		}

		@Override
		public boolean hasNext() {
			return cursor < size();
		}

		@Override
		public T next() {
			if (hasNext()) {
				if (cursor < readOnlyList.size()) {
					return readOnlyList.get(cursor++);
				}
				return readAndWriteList.get(getModifiableListStartIndex(cursor++));
			}
			throw new NoSuchElementException();
		}
	}
}
