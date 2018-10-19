package com.enzo.greadfood.domain.interactors.impl;

import com.enzo.greadfood.domain.executor.Executor;
import com.enzo.greadfood.domain.executor.MainThread;
import com.enzo.greadfood.domain.interactors.SignupInteractor;
import com.enzo.greadfood.domain.interactors.base.AbstractInteractor;
import com.enzo.greadfood.domain.repository.SignupRepository;
import com.enzo.greadfood.util.CustomLog;

public class SignupInteractorImpl extends AbstractInteractor implements SignupInteractor {
    private SignupInteractor.Callback callback;
    private SignupRepository repository;

    public SignupInteractorImpl(Executor threadExecutor, MainThread mainThread, SignupInteractor.Callback callback, SignupRepository repository) {
        super(threadExecutor, mainThread);
        this.callback = callback;
        this.repository = repository;
    }

    @Override
    public void run() {
        final boolean isSignup = repository.isSignup();
        CustomLog.i("SingupInteractor", "isSignup " + isSignup);
        if (isSignup){
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSignupSuccess();
                }
            });
        }else{
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSignupFail();
                }
            });
        }
    }
}
