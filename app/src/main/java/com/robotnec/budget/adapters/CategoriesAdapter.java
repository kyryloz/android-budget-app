package com.robotnec.budget.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoriesAdapter extends BaseAdapter<Category, CategoriesAdapter.ViewHolder> {

    private ColorGenerator colorGenerator;
    private CategoryClickListener categoryClickListener;

    public CategoriesAdapter(Context context, CategoryClickListener listener) {
        super(context);
        colorGenerator = ColorGenerator.MATERIAL;
        categoryClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Category category = items.get(position);
        String name = category.getName();

        holder.mTextName.setText(name);

        int iconColor = colorGenerator.getColor(name);
        TextDrawable iconDrawable = TextDrawable.builder().buildRound(name.substring(0, 1), iconColor);
        holder.mImageIcon.setImageDrawable(iconDrawable);

        holder.itemView.setOnClickListener(v -> {
            if (categoryClickListener != null) {
                categoryClickListener.onCategoryClick(items.get(holder.getAdapterPosition()));
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_category_name)
        TextView mTextName;

        @BindView(R.id.image_icon)
        ImageView mImageIcon;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface CategoryClickListener {
        void onCategoryClick(Category category);
    }
}
