package com.enzo.greadfood.presentation.presenter.impl;


import android.content.Context;

import com.enzo.greadfood.domain.executor.Executor;
import com.enzo.greadfood.domain.executor.MainThread;
import com.enzo.greadfood.domain.interactors.CheckLoginInteractor;
import com.enzo.greadfood.domain.interactors.impl.CheckLoginInteractorImpl;
import com.enzo.greadfood.domain.repository.UserRepository;
import com.enzo.greadfood.pojo.FireBase.FireBasesGetUserImpl;
import com.enzo.greadfood.presentation.presenter.LoginPresenter;
import com.enzo.greadfood.presentation.presenter.Bases.AbstractPresenter;


public class LoginPresenterImp extends AbstractPresenter implements LoginPresenter, CheckLoginInteractor.Callback{
    private LoginPresenter.View mView;

    public LoginPresenterImp(Executor executor, MainThread mainThread, LoginPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }



    @Override
    public void clickNotify(Context context) {

    }

    @Override
    public void onClickEventLogin(String id, String pass) {
        mView.showProgress();
        UserRepository mUserRepository = FireBasesGetUserImpl.getInstance(id, pass);
        CheckLoginInteractor mCheckLoginInteractor = new CheckLoginInteractorImpl(
                mExecutor, mMainThread, this, mUserRepository);
        mCheckLoginInteractor.execute();
    }

    @Override
    public void resume() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(Boolean isLogin) {
        mView.hideProgress();
        mView.showLogin(isLogin);
    }


    @Override
    public void onFail() {
        mView.hideProgress();
        mView.showError("Id or password is incorrect!");
    }

}
