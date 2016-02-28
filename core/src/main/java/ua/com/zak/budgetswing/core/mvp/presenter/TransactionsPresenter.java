package ua.com.zak.budgetswing.core.mvp.presenter;

import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.TransactionsView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsPresenter extends Presenter<TransactionsView> {

    public TransactionsPresenter(TransactionsView view) {
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
