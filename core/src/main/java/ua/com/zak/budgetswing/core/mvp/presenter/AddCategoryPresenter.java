package ua.com.zak.budgetswing.core.mvp.presenter;

import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.AddCategoryView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddCategoryPresenter extends Presenter<AddCategoryView> {

    public AddCategoryPresenter(AddCategoryView view) {
        super(view);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {

    }
}
