package com.enzo.greadfood.util;


import com.enzo.greadfood.domain.executor.impl.ThreadExecutor;
import com.enzo.greadfood.presentation.presenter.HomePresenter;
import com.enzo.greadfood.presentation.presenter.LoginPresenter;
import com.enzo.greadfood.presentation.presenter.SignupPresenter;
import com.enzo.greadfood.presentation.presenter.impl.HomePresenterImpl;
import com.enzo.greadfood.presentation.presenter.impl.LoginPresenterImp;
import com.enzo.greadfood.presentation.presenter.impl.SignupPresenterImpl;
import com.enzo.greadfood.threading.MainThreadImpl;

public class Injection {
    private static Injection instance;
    public static Injection getInstance(){
        if (instance == null){
            instance = new Injection();
        }
        return instance;
    }

    public LoginPresenter getLoginPresenter(LoginPresenter.View view){
        return new LoginPresenterImp(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), view);
    }

    public SignupPresenter getSignupPresenter(SignupPresenter.View view){
        return new SignupPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), view);
    }

    public HomePresenter getHomePresenter(HomePresenter.View view){
        return new HomePresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), view);
    }

}
