package com.enzo.greadfood.presentation.presenter.impl;

import com.enzo.greadfood.domain.executor.Executor;
import com.enzo.greadfood.domain.executor.MainThread;
import com.enzo.greadfood.domain.interactors.SignupInteractor;
import com.enzo.greadfood.domain.interactors.impl.SignupInteractorImpl;
import com.enzo.greadfood.domain.repository.SignupRepository;
import com.enzo.greadfood.pojo.FireBase.SignupRepositoryImpl;
import com.enzo.greadfood.presentation.presenter.SignupPresenter;
import com.enzo.greadfood.presentation.presenter.Bases.AbstractPresenter;

public class SignupPresenterImpl extends AbstractPresenter implements SignupPresenter, SignupInteractor.Callback {
    SignupPresenter.View mView;
    public SignupPresenterImpl(Executor executor, MainThread mainThread, SignupPresenter.View mView) {
        super(executor, mainThread);
        this.mView = mView;
    }

    @Override
    public void onClickEventSignup(String phone, String name, String pass) {
        mView.showProgress();
        SignupRepository mSignupRepository = SignupRepositoryImpl.getInstance(phone, name, pass);
        SignupInteractor mSignupInteractor = new SignupInteractorImpl(
                mExecutor,mMainThread, this, mSignupRepository);
        mSignupInteractor.execute();
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
    public void onSignupSuccess() {
        mView.hideProgress();
        mView.showSignup();
    }

    @Override
    public void onSignupFail() {
        mView.hideProgress();
        mView.showError("Phone number was signup!");
    }
}
