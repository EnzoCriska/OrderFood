package com.enzo.greadfood.domain.interactors.impl;

import com.enzo.greadfood.domain.executor.Executor;
import com.enzo.greadfood.domain.executor.MainThread;
import com.enzo.greadfood.domain.interactors.CheckLoginInteractor;
import com.enzo.greadfood.domain.interactors.base.AbstractInteractor;
import com.enzo.greadfood.domain.repository.UserRepository;

public class CheckLoginInteractorImpl extends AbstractInteractor implements CheckLoginInteractor {
    private CheckLoginInteractor.Callback callback;
    private UserRepository userRepository;

    public CheckLoginInteractorImpl(Executor threadExecutor, MainThread mainThread, CheckLoginInteractor.Callback callback, UserRepository userRepository) {
        super(threadExecutor, mainThread);
        this.callback = callback;
        this.userRepository = userRepository;
    }

    @Override
    public void run() {
        final Boolean isLogin = userRepository.checkLogin();
        if (isLogin){
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess(isLogin);
                }
            });
        }else{
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onFail();
                }
            });
        }

    }
}
