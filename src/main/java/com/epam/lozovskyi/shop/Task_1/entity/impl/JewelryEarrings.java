package com.epam.lozovskyi.shop.Task_1.entity.impl;

import com.epam.lozovskyi.shop.Task_1.entity.Jewelry;

import java.util.Objects;

public class JewelryEarrings extends Jewelry {

	private static final String DEFAULT_STYLE = "butterfly";
	private String style;

	public JewelryEarrings() {
		super();
		this.style = DEFAULT_STYLE;
	}


	public JewelryEarrings(int id, int price, String name, String material, String color, int weight, int hallmark, String style) {
		super(id, price, name, material, color, weight, hallmark);
		this.style = style;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public String toString() {

		return super.toString() + "; style='" + style + '\'';
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
		JewelryEarrings that = (JewelryEarrings) object;
		return Objects.equals(style, that.style);
	}

	@Override
	public int hashCode() {
		int hash = 31 * super.hashCode() + (style != null ? style.hashCode() : 0);
		return hash;
	}
}
