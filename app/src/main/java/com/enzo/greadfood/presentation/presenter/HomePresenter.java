package com.enzo.greadfood.presentation.presenter;

import com.enzo.greadfood.domain.model.Category;
import com.enzo.greadfood.presentation.UI.BaseView;
import com.enzo.greadfood.presentation.presenter.Bases.BasePresenter;

import java.util.ArrayList;

public interface HomePresenter extends BasePresenter {
    void getCategoryFromFireBase();
    interface View extends BaseView {
        void showCategory(ArrayList<Category> list);
    }
}
