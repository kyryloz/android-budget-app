package com.robotnec.budget.app.adapters.support

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * @author zak zak@swingpulse.com>
 */
interface ListHeader {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder)
}
