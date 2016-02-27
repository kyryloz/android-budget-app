package ua.com.zak.budgetswing.core.mvp.presenter;

import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.MainView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MainPresenter extends Presenter<MainView> {

    public MainPresenter(MainView view) {
        super(view);
    }

    @Override
    public void bindView() {
        mView.initView();
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }
}
