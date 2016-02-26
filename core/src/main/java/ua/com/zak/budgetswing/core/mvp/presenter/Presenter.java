package ua.com.zak.budgetswing.core.mvp.presenter;


import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.MvpView;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class Presenter<V extends MvpView> {

    protected final V mView;

    public Presenter(V view) {
        mView = view;
    }

    public abstract void bindView();

    public abstract void injectComponent(ApplicationComponent applicationComponent);
}
