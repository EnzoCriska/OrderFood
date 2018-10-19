package com.enzo.greadfood.domain.repository;

import com.enzo.greadfood.domain.model.Food;

import java.util.ArrayList;

public interface FoodListRepository {
    ArrayList<Food> getFoodList();
}
