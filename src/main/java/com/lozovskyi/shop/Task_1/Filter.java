package com.lozovskyi.shop.Task_1;

import com.lozovskyi.shop.Task_1.entity.Product;

public interface Filter<T extends Product> {

	boolean condition(T product);
}
