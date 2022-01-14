package com.epam.lozovskyi.shop.Task_1.entity;


import java.io.Serializable;

public abstract class Product implements Serializable {
	private static final int DEFAULT_ID = 1;
	private static final int DEFAULT_PRICE = 500;

	protected int id;
	private int price;
	protected String name;

	public Product() {
		this(DEFAULT_ID, DEFAULT_PRICE, "");
	}

	public Product(int id, int price, String name) {
		this.id = id;
		this.price = price;
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "price=" + price + "; ";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Product product = (Product) o;
		return id == product.id;
	}

	@Override
	public int hashCode() {
		int hash = 31 * id;
		hash = 31 * hash + price;
		return hash;
	}
}
