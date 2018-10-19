package com.enzo.greadfood.domain.interactors;

import com.enzo.greadfood.domain.interactors.base.Interactor;
import com.enzo.greadfood.domain.model.Category;

import java.util.ArrayList;

public interface HomeInteractor extends Interactor {
    interface CallBack{
        void onLoadCategorySuccess(ArrayList<Category> list);
        void onLoadCategoryFail();
    }

}
