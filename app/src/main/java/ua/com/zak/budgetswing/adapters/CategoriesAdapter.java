package ua.com.zak.budgetswing.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoriesAdapter extends BaseAdapter<Category, CategoriesAdapter.ViewHolder> {

    private ColorGenerator mColorGenerator;
    private CategoryClickListener mListener;

    public CategoriesAdapter(Context context, CategoryClickListener listener) {
        super(context);
        mColorGenerator = ColorGenerator.MATERIAL;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Category category = mItems.get(position);
        String name = category.getName();

        holder.mTextName.setText(name);

        int iconColor = mColorGenerator.getColor(name);
        TextDrawable iconDrawable = TextDrawable.builder().buildRound(name.substring(0, 1), iconColor);
        holder.mImageIcon.setImageDrawable(iconDrawable);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCategoryClick(mItems.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_category_name)
        TextView mTextName;

        @Bind(R.id.image_icon)
        ImageView mImageIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface CategoryClickListener {
        void onCategoryClick(Category category);
    }
}
