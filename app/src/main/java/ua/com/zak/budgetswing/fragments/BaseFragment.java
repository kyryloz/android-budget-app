package ua.com.zak.budgetswing.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ua.com.zak.budgetswing.ApplicationModelProvider;
import ua.com.zak.budgetswing.model.ApplicationModel;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BaseFragment extends Fragment {

    protected ApplicationModel mApplicationModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mApplicationModel = ((ApplicationModelProvider) context).getApplicationModel();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mApplicationModel = null;
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container);
        ButterKnife.bind(this, root);
        return root;
    }

    protected abstract int getLayoutId();
}
