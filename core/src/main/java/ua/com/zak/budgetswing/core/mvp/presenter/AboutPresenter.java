package ua.com.zak.budgetswing.core.mvp.presenter;

import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.AboutView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AboutPresenter extends Presenter<AboutView> {

    public AboutPresenter(AboutView view) {
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
