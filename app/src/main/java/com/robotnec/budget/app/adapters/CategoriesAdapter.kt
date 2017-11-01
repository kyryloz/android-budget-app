package com.robotnec.budget.app.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.support.BaseAdapter
import com.robotnec.budget.app.util.TextIconUtils
import com.robotnec.budget.core.domain.Category
import kotlinx.android.synthetic.main.item_category.view.imageIcon
import kotlinx.android.synthetic.main.item_category.view.textCategoryName

/**
 * @author zak zak@swingpulse.com>
 */
class CategoriesAdapter(context: Context, private val categoryClickListener: CategoriesAdapter.CategoryClickListener)
    : BaseAdapter<Category, CategoriesAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_category, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (_, name) = items[position]

        holder.itemView.textCategoryName.text = name
        holder.itemView.imageIcon.setImageDrawable(TextIconUtils.generate(name))
        holder.itemView.setOnClickListener { v ->
            categoryClickListener.onCategoryClick(items[holder.adapterPosition])
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface CategoryClickListener {
        fun onCategoryClick(category: Category)
    }
}
