package ua.com.zak.mvpandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ua.com.zak.mvpcore.presenter.Presenter;
import ua.com.zak.mvpcore.view.MvpView;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BasePresenterFragment<P extends Presenter> extends Fragment implements MvpView {

    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, root);
        mPresenter = createPresenter();
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter.initView();
    }

    protected abstract int getLayoutId();

    protected abstract P createPresenter();
}
