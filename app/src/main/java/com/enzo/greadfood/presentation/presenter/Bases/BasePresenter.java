package com.enzo.greadfood.presentation.presenter.Bases;

public interface BasePresenter {
    void resume();
    void stop();
    void pause();
    void destroy();
    void onError();
}
