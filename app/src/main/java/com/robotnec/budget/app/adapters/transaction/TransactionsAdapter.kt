package com.robotnec.budget.app.adapters.transaction

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation
import java.util.ArrayList

/**
 * @author zak zak@swingpulse.com>
 */
class TransactionsAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<TransactionListItem>
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    init {
        items = ArrayList()
        setHasStableIds(true)
    }

    fun update(aggregation: TransactionAggregation) {
        this.items.clear()
        this.items.addAll(toTransactionListItems(aggregation))
        notifyDataSetChanged()
    }

    private fun toTransactionListItems(aggregation: TransactionAggregation): List<TransactionListItem> {
        val items = mutableListOf<TransactionListItem>()

        aggregation[TransactionAggregation.Sorting.DESC]
                .forEach { (span, transactions) ->
                    items.add(HeaderItem(span.startDate.toLocalDate(), aggregation.getSum(span)))
                    items.addAll(transactions
                            .map { TransactionItem(it) }
                            .toList())
                }
        return items
    }

    override fun getItemId(position: Int): Long {
        return items[position].id
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            HeaderItem.createViewHolder(layoutInflater, parent)
        } else {
            TransactionItem.createViewHolder(layoutInflater, parent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].bindViewHolder(holder)
    }

    companion object {
        internal const val VIEW_TYPE_HEADER = 1
        internal const val VIEW_TYPE_ITEM = 2
    }
}
