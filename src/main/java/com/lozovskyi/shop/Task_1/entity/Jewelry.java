package com.lozovskyi.shop.Task_1.entity;

import java.util.Objects;

public abstract class Jewelry extends Product {
	private static final String DEFAULT_MATERIAL = "gold";

	private static final String DEFAULT_COLOR = "yellow";
	private static final int DEFAULT_KARAT = 10;
	private static final int DEFAULT_HALLMARK = 925;

	private String material; //gold, silver1
	private String color; //gold, silver1
	private int karat; // gram
	private int hallmark;

	public Jewelry() {
		this.material = DEFAULT_MATERIAL;
		this.color = DEFAULT_COLOR;
		this.karat = DEFAULT_KARAT;
		this.hallmark = DEFAULT_HALLMARK;
	}

	public Jewelry(int id, int price, String name, String material, String color, int weight, int hallmark) {
		super(id, price, name);

		this.material = material;
		this.color = color;
		this.karat = weight;
		this.hallmark = hallmark;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getKarat() {
		return karat;
	}

	public void setKarat(int karat) {
		this.karat = karat;
	}

	public int getHallmark() {
		return hallmark;
	}

	public void setHallmark(int hallmark) {
		this.hallmark = hallmark;
	}

	@Override
	public String toString() {
		return super.toString() + "material='" + material + '\'' +
				"; color='" + color + '\'' +
				"; karat=" + karat + '\'' +
				"; hallmark=" + hallmark;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Jewelry jewelry = (Jewelry) o;

		return karat == jewelry.karat &&
				hallmark == jewelry.hallmark &&
				Objects.equals(material, jewelry.material) &&
				Objects.equals(color, jewelry.color);
	}

	@Override
	public int hashCode() {
		int hash = 31 * super.hashCode() + (material != null ? material.hashCode() : 0);
		hash = 31 * hash + (color != null ? color.hashCode() : 0);
		hash = 31 * hash + karat;
		hash = 31 * hash + hallmark;
		return hash;
	}
}
