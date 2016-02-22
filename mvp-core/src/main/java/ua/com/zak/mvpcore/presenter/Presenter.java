package ua.com.zak.mvpcore.presenter;


import ua.com.zak.mvpcore.view.MvpView;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class Presenter<V extends MvpView> {

    protected final V mView;
    protected final BaseApplicationComponent mComponent;

    public Presenter(V view, BaseApplicationComponent component) {
        mView = view;
        mComponent = component;
    }

    public abstract void initView();
}
