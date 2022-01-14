package com.epam.lozovskyi.shop.Task_1;

import com.epam.lozovskyi.shop.Task_1.entity.Product;

public interface Filter<T extends Product> {

	boolean condition(T product);
}
