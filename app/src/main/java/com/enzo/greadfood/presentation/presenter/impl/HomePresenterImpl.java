package com.enzo.greadfood.presentation.presenter.impl;

import com.enzo.greadfood.domain.executor.Executor;
import com.enzo.greadfood.domain.executor.MainThread;
import com.enzo.greadfood.domain.executor.impl.ThreadExecutor;
import com.enzo.greadfood.domain.interactors.HomeInteractor;
import com.enzo.greadfood.domain.interactors.impl.HomeInteractorImpl;
import com.enzo.greadfood.domain.model.Category;
import com.enzo.greadfood.domain.repository.HomeRepository;
import com.enzo.greadfood.pojo.FireBase.HomeRepositoryImpl;
import com.enzo.greadfood.presentation.presenter.Bases.AbstractPresenter;
import com.enzo.greadfood.presentation.presenter.HomePresenter;

import java.util.ArrayList;

public class HomePresenterImpl extends AbstractPresenter implements HomePresenter, HomeInteractor.CallBack {
    private HomePresenter.View view;
    public HomePresenterImpl(Executor executor, MainThread mainThread, HomePresenter.View view) {
        super(executor, mainThread);
        this.view = view;
    }

    @Override
    public void getCategoryFromFireBase() {
        view.showProgress();
        HomeRepository mHomeRepository = HomeRepositoryImpl.getInstance();
        HomeInteractor mHomeInteractor = new HomeInteractorImpl(mExecutor, mMainThread, this, mHomeRepository);
        mHomeInteractor.execute();
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
    public void onLoadCategorySuccess(ArrayList<Category> list) {
        view.hideProgress();
        view.showCategory(list);
    }

    @Override
    public void onLoadCategoryFail() {
        view.hideProgress();
        view.showError("No data Loaded");
    }
}
