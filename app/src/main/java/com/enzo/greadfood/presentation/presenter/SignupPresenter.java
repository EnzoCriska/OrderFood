package com.enzo.greadfood.presentation.presenter;

import com.enzo.greadfood.presentation.UI.BaseView;
import com.enzo.greadfood.presentation.presenter.Bases.BasePresenter;

public interface SignupPresenter extends BasePresenter {

    void onClickEventSignup(String phone, String name, String pass);

    interface View extends BaseView {
        void showSignup();
    }
}
