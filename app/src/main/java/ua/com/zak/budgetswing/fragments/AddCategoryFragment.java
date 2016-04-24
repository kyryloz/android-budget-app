package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.Bind;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.mvp.presenter.AddCategoryPresenter;
import ua.com.zak.budgetswing.core.mvp.view.AddCategoryView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddCategoryFragment extends BasePresenterFragment<AddCategoryPresenter>
        implements AddCategoryView {

    @Bind(R.id.edit_category_name)
    EditText mEditCategoryName;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_category;
    }

    @Override
    protected AddCategoryPresenter createPresenter() {
        return new AddCategoryPresenter(this);
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
                mPresenter.addCategory(mEditCategoryName.getText().toString());
                getActivity().finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
