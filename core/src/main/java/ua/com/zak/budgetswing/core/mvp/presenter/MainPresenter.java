package ua.com.zak.budgetswing.core.mvp.presenter;

import ua.com.zak.budgetswing.core.mvp.view.MainView;
import ua.com.zak.mvpcore.presenter.Presenter;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MainPresenter extends Presenter<MainView> {

    public MainPresenter(MainView view) {
        super(view);
    }

    @Override
    public void initView() {

    }
}
