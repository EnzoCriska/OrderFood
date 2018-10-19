package com.enzo.greadfood.presentation.presenter;

import com.enzo.greadfood.domain.model.Food;
import com.enzo.greadfood.presentation.UI.BaseView;
import com.enzo.greadfood.presentation.presenter.Bases.BasePresenter;

import java.util.ArrayList;

public interface FoodListPresenter extends BasePresenter {
    void getFoodListFromFireBase(String menu_id);
    interface View extends BaseView {
        void showFoodList(ArrayList<Food> list);
    }
}
