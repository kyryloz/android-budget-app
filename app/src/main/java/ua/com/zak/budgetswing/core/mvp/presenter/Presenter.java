package ua.com.zak.budgetswing.core.mvp.presenter;


import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.View;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class Presenter<V extends View> {

    protected final V mView;

    public Presenter(V view) {
        mView = view;
    }

    public abstract void injectComponent(ApplicationComponent applicationComponent);

    public abstract void onViewReady();
}
