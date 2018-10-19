package com.enzo.greadfood.domain.repository;

import com.enzo.greadfood.domain.model.Category;

import java.util.ArrayList;

public interface HomeRepository {
    ArrayList<Category> getCategory();
}
