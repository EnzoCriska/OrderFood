package com.enzo.greadfood.domain.interactors.impl;

import com.enzo.greadfood.domain.executor.Executor;
import com.enzo.greadfood.domain.executor.MainThread;
import com.enzo.greadfood.domain.interactors.FoodListInteractor;
import com.enzo.greadfood.domain.interactors.base.AbstractInteractor;
import com.enzo.greadfood.domain.model.Category;
import com.enzo.greadfood.domain.model.Food;
import com.enzo.greadfood.domain.repository.FoodListRepository;

import java.util.ArrayList;

public class FoodListInteractorImpl extends AbstractInteractor implements FoodListInteractor {
    private FoodListInteractor.CallBack callBack;
    private FoodListRepository repository;

    public FoodListInteractorImpl(Executor threadExecutor, MainThread mainThread, FoodListInteractor.CallBack callBack, FoodListRepository repository) {
        super(threadExecutor, mainThread);
        this.callBack = callBack;
        this.repository = repository;
    }


    @Override
    public void run() {
        final ArrayList<Food> list = repository.getFoodList();
        if (!list.isEmpty()){
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onLoadFoodListSuccess(list);
                }
            });
        }else{
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onLoadFoodListFail();
                }
            });
        }
    }
}
