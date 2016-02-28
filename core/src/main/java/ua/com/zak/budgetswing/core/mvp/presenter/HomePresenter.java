package ua.com.zak.budgetswing.core.mvp.presenter;

import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.HomeView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class HomePresenter extends Presenter<HomeView> {

    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    public void bindView() {

    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }
}
