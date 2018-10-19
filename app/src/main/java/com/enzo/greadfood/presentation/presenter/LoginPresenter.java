package com.enzo.greadfood.presentation.presenter;

import android.content.Context;


import com.enzo.greadfood.presentation.UI.BaseView;
import com.enzo.greadfood.presentation.presenter.Bases.BasePresenter;

public interface LoginPresenter extends BasePresenter {


    void clickNotify(Context context);

    void onClickEventLogin(String id, String pass);

    interface View extends BaseView {
        void showLogin(Boolean isLogin);
    }
}
