package com.enzo.greadfood.domain.interactors.impl;

import com.enzo.greadfood.domain.executor.Executor;
import com.enzo.greadfood.domain.executor.MainThread;
import com.enzo.greadfood.domain.interactors.HomeInteractor;
import com.enzo.greadfood.domain.interactors.base.AbstractInteractor;
import com.enzo.greadfood.domain.model.Category;
import com.enzo.greadfood.domain.repository.HomeRepository;

import java.util.ArrayList;

public class HomeInteractorImpl extends AbstractInteractor implements HomeInteractor {
    private HomeInteractor.CallBack callBack;
    private HomeRepository repository;
    public HomeInteractorImpl(Executor threadExecutor, MainThread mainThread, HomeInteractor.CallBack callBack, HomeRepository repository) {
        super(threadExecutor, mainThread);
        this.callBack = callBack;
        this.repository = repository;
    }

    @Override
    public void run() {
        final ArrayList<Category> list = repository.getCategory();
        if (!list.isEmpty()){
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onLoadCategorySuccess(list);
                }
            });
        }else{
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onLoadCategoryFail();
                }
            });
        }
    }
}
