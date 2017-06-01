package com.robotnec.budget.app.adapters.transaction

import android.support.v7.widget.RecyclerView

/**
 * @author zak zak@swingpulse.com>
 */
internal interface TransactionListItem {
    val viewType: Int

    val id: Long

    fun bindViewHolder(viewHolder: RecyclerView.ViewHolder)
}
