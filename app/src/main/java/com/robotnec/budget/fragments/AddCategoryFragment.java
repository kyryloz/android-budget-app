package com.robotnec.budget.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.mvp.presenter.AddCategoryPresenter;
import com.robotnec.budget.core.mvp.view.AddCategoryView;
import com.robotnec.budget.util.Keys;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddCategoryFragment extends BasePresenterFragment<AddCategoryPresenter>
        implements AddCategoryView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.edit_category_name)
    EditText editCategoryName;

    @BindView(R.id.button_delete)
    Button buttonDelete;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbarBack(toolbar);
    }

    @OnClick(R.id.button_delete)
    void onDeleteClick() {
        presenter.deleteCategory();
    }

    @OnClick(R.id.button_done)
    void onDoneClick() {
        presenter.addOrEditCategory(editCategoryName.getText().toString());
        getActivity().finish();
    }

    @Override
    public void initEditMode(Category category) {
        editCategoryName.setText(category.getName());
        editCategoryName.setSelection(editCategoryName.getText().length());
        buttonDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeView() {
        getActivity().finish();
    }
}
