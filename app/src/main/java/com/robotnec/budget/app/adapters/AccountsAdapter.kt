package com.robotnec.budget.app.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.support.BaseAdapter
import com.robotnec.budget.core.domain.Account
import kotlinx.android.synthetic.main.item_account.view.textAmount
import kotlinx.android.synthetic.main.item_account.view.textName

/**
 * @author zak zak@swingpulse.com>
 */
class AccountsAdapter(context: Context,
                      private val accountClickListener: AccountsAdapter.AccountClickListener)
    : BaseAdapter<Account, AccountsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_account, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (_, name, amount) = items[position]
        holder.itemView.textName.text = name
        holder.itemView.textAmount.text = amount.toString()
        holder.itemView.setOnClickListener {
            accountClickListener.onAccountClick(items[holder.adapterPosition])
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface AccountClickListener {
        fun onAccountClick(account: Account)
    }
}
