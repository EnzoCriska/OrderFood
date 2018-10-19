package com.enzo.greadfood.domain.interactors;

import com.enzo.greadfood.domain.interactors.base.Interactor;
import com.enzo.greadfood.domain.model.Food;

import java.util.ArrayList;

public interface FoodListInteractor extends Interactor {
    interface CallBack{
        void onLoadFoodListSuccess(ArrayList<Food> list);
        void onLoadFoodListFail();
    }
}
