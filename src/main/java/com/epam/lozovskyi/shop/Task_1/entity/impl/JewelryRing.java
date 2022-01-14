package com.epam.lozovskyi.shop.Task_1.entity.impl;

import com.epam.lozovskyi.shop.Task_1.entity.Jewelry;

import java.util.Objects;

public class JewelryRing extends Jewelry {
	private static final String DEFAULT_shape = "round";
	private String shape;

	public JewelryRing() {
		super();
		this.shape = DEFAULT_shape;
	}


	public JewelryRing(int id, int price, String name, String material, String color, int weight, int hallmark, String shape) {
		super(id, price, name, material, color, weight, hallmark);
		this.shape = shape;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	@Override
	public String toString() {
		return super.toString() + "; shape='" + shape + '\'';
	}

	@Override

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		if (!super.equals(object)) {
			return false;
		}
		JewelryRing that = (JewelryRing) object;
		return Objects.equals(shape, that.shape);
	}

	@Override
	public int hashCode() {
		int hash = 31 * super.hashCode() + (shape != null ? shape.hashCode() : 0);

		return hash;
	}
}
