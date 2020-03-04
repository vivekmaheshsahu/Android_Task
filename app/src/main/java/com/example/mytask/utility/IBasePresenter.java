package com.example.mytask.utility;

public interface IBasePresenter<V> {

    void attachView(V view);

    void detachView();

}
