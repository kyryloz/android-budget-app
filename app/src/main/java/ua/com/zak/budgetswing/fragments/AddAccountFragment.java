package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.Bind;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.mvp.presenter.AddAccountPresenter;
import ua.com.zak.budgetswing.core.mvp.view.AddAccountView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddAccountFragment extends BasePresenterFragment<AddAccountPresenter>
        implements AddAccountView {

    @Bind(R.id.edit_account_name)
    EditText mEditAccountName;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_account;
    }

    @Override
    protected AddAccountPresenter createPresenter() {
        return new AddAccountPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_done, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_done:
                mPresenter.addAccount(mEditAccountName.getText().toString());
                getActivity().finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
