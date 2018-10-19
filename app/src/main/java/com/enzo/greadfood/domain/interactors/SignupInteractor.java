package com.enzo.greadfood.domain.interactors;

import com.enzo.greadfood.domain.interactors.base.Interactor;

public interface SignupInteractor extends Interactor {
    interface Callback{
        void onSignupSuccess();
        void onSignupFail();
    }
}
