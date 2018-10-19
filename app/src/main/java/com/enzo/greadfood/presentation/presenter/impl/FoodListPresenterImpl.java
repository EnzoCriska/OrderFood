package com.enzo.greadfood.presentation.presenter.impl;

import com.enzo.greadfood.domain.executor.Executor;
import com.enzo.greadfood.domain.executor.MainThread;
import com.enzo.greadfood.domain.interactors.FoodListInteractor;
import com.enzo.greadfood.domain.interactors.impl.FoodListInteractorImpl;
import com.enzo.greadfood.domain.model.Food;
import com.enzo.greadfood.domain.repository.FoodListRepository;
import com.enzo.greadfood.pojo.FireBase.FoodListRepositoryImpl;
import com.enzo.greadfood.presentation.presenter.Bases.AbstractPresenter;
import com.enzo.greadfood.presentation.presenter.FoodListPresenter;

import java.util.ArrayList;

public class FoodListPresenterImpl extends AbstractPresenter implements FoodListPresenter, FoodListInteractor.CallBack {
    private FoodListPresenter.View view;

    public FoodListPresenterImpl(Executor executor, MainThread mainThread, FoodListPresenter.View view) {
        super(executor, mainThread);
        this.view = view;
    }

    @Override
    public void getFoodListFromFireBase(String menu_id) {
        view.showProgress();
        FoodListRepository mFoodListRepository = FoodListRepositoryImpl.getInstance(menu_id);
        FoodListInteractor mFoodListInteractor = new FoodListInteractorImpl(mExecutor, mMainThread, this, mFoodListRepository);
        mFoodListInteractor.execute();

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
    public void onLoadFoodListSuccess(ArrayList<Food> list) {
        view.hideProgress();
        view.showFoodList(list);
    }

    @Override
    public void onLoadFoodListFail() {
        view.hideProgress();
        view.showError("Not Food in list");
    }
}
