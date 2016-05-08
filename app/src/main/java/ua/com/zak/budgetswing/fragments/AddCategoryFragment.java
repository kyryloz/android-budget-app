package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.domain.Category;
import ua.com.zak.budgetswing.core.mvp.presenter.AddCategoryPresenter;
import ua.com.zak.budgetswing.core.mvp.view.AddCategoryView;
import ua.com.zak.budgetswing.util.Keys;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddCategoryFragment extends BasePresenterFragment<AddCategoryPresenter>
        implements AddCategoryView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.edit_category_name)
    EditText mEditCategoryName;

    public static AddCategoryFragment newInstance(Category category) {
        AddCategoryFragment fragment = new AddCategoryFragment();
        Bundle args = new Bundle();
        args.putSerializable(Keys.CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_category;
    }

    @Override
    protected AddCategoryPresenter createPresenter() {
        Category category = (Category) getArguments().getSerializable(Keys.CATEGORY);
        return new AddCategoryPresenter(this, category);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbarBack(mToolbar);
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
                mPresenter.addOrEditCategory(mEditCategoryName.getText().toString());
                getActivity().finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initEditMode(Category category) {
        mEditCategoryName.setText(category.getName());
        mEditCategoryName.setSelection(mEditCategoryName.getText().length());
    }
}
