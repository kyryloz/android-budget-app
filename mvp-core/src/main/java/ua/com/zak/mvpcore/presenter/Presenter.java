package ua.com.zak.mvpcore.presenter;


import ua.com.zak.mvpcore.view.MvpView;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class Presenter<V extends MvpView> {

    protected V mView;

    public Presenter(V view) {
        mView = view;
    }

    public abstract void initView();
}
