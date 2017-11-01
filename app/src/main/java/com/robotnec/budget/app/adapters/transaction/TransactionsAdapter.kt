package com.robotnec.budget.app.adapters.transaction

import android.content.Context
import android.support.v7.widget.RecyclerView
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
        items = ArrayList<TransactionListItem>()
        setHasStableIds(true)
    }

    fun update(aggregation: TransactionAggregation) {
        this.items.clear()
        this.items.addAll(toTransactionListItems(aggregation))
        notifyDataSetChanged()
    }

    private fun toTransactionListItems(aggregation: TransactionAggregation): List<TransactionListItem> {
        val items = mutableListOf<TransactionListItem>()

        aggregation.get(TransactionAggregation.Sorting.DESC)
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
        if (viewType == VIEW_TYPE_HEADER) {
            return HeaderItem.createViewHolder(layoutInflater, parent)
        } else {
            return TransactionItem.createViewHolder(layoutInflater, parent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].bindViewHolder(holder)
    }

    companion object {
        internal val VIEW_TYPE_HEADER = 1
        internal val VIEW_TYPE_ITEM = 2
    }
}
