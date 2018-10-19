package com.enzo.greadfood.domain.interactors;

import com.enzo.greadfood.domain.interactors.base.Interactor;

public interface CheckLoginInteractor extends Interactor {
    interface Callback{
        void onSuccess(Boolean isLogin);
        void onFail();
    }
}
