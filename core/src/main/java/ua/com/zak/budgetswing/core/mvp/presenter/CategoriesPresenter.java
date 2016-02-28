package ua.com.zak.budgetswing.core.mvp.presenter;

import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.CategoriesView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoriesPresenter extends Presenter<CategoriesView> {

    public CategoriesPresenter(CategoriesView view) {
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
