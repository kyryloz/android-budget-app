package com.robotnec.budget.app.adapters.support

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import java.util.ArrayList

/**
 * @author zak zak@swingpulse.com>
 */
abstract class BaseAdapter<T, S : RecyclerView.ViewHolder>(
        protected val context: Context) : RecyclerView.Adapter<S>() {
    protected val items: MutableList<T>
    protected val layoutInflater: LayoutInflater

    init {
        items = ArrayList<T>()
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
