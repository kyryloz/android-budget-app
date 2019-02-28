package com.robotnec.budget.app.adapters.transaction

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robotnec.budget.R
import com.robotnec.budget.app.util.TextIconUtils
import com.robotnec.budget.core.domain.operation.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.imageIcon
import kotlinx.android.synthetic.main.item_transaction.view.textAccountName
import kotlinx.android.synthetic.main.item_transaction.view.textAmount
import kotlinx.android.synthetic.main.item_transaction.view.textCategoryName

/**
 * @author zak zak@swingpulse.com>
 */
internal class TransactionItem(private val transaction: Transaction) : TransactionListItem {

    override val viewType: Int = TransactionsAdapter.VIEW_TYPE_ITEM

    override val id: Long = transaction.id

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val holder = viewHolder as TransactionViewHolder

        with(holder.itemView) {
            imageIcon.setImageDrawable(TextIconUtils.generate(transaction.category.name))
            textAmount.text = resources.getString(R.string.negative_amount, transaction.amount)
            textAccountName.text = transaction.account.name
            textCategoryName.text = transaction.category.name
            textAmount.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        }

        // TODO operation sign
        //        if (transaction.getAmount().isNegative()) {
        //        } else {
        //            holder.textAmount.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        //        }
    }

    internal class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {

        fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
            val itemView = inflater.inflate(R.layout.item_transaction, parent, false)
            return TransactionViewHolder(itemView)
        }
    }
}
