package ua.com.zak.budgetswing.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.domain.Account;
import ua.com.zak.budgetswing.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoriesAdapter extends BaseAdapter<Category, CategoriesAdapter.ViewHolder> {

    public CategoriesAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = mItems.get(position);
        holder.mTextName.setText(category.getName());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_category_name)
        TextView mTextName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
