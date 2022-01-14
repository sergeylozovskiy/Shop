package com.epam.lozovskyi.shop.Task_4.strategy;

import com.epam.lozovskyi.shop.Task_1.entity.Product;
import com.epam.lozovskyi.shop.Task_4.input.InputHelper;

public interface CreationStrategy {

	Product createProduct(InputHelper inputHelper);
}
