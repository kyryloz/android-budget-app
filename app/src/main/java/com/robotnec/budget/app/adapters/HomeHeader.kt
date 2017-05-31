package com.robotnec.budget.app.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.support.ListHeader
import com.robotnec.budget.core.domain.Account
import kotlinx.android.synthetic.main.view_home_header.view.*
import java.util.*

/**
 * @author zak zak@swingpulse.com>
 */
class HomeHeader : ListHeader {

    private val accounts: MutableList<Account>

    init {
        accounts = ArrayList<Account>()
    }

    fun update(accounts: List<Account>) {
        this.accounts.clear()
        val toIndex = if (accounts.size > 2) 2 else accounts.size
        this.accounts.addAll(accounts.subList(0, toIndex))
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.view_home_header, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder) {
        if (accounts.isEmpty()) {
            return
        }
        val viewHolder = holder as ViewHolder
        viewHolder.bind(accounts)
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(accounts: List<Account>) {
            itemView.textAmount.text = accounts[0].amount.toDisplayableString()
            itemView.textAmountSecond.text = accounts[1].amount.toDisplayableString()
            itemView.textName.text = accounts[0].name
            itemView.textNameSecond.text = accounts[1].name
        }
    }
}
