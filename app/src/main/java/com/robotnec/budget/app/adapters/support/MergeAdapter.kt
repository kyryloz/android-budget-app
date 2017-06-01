package com.robotnec.budget.app.adapters.support

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * @author zak zak@swingpulse.com>
 */
class MergeAdapter(private val first: RecyclerView.Adapter<RecyclerView.ViewHolder>,
                   private val second: RecyclerView.Adapter<RecyclerView.ViewHolder>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        val target = findTargetAdapter(position)
        val normalPosition = normalizePosition(target, position)
        return target.getItemId(normalPosition)
    }

    override fun getItemViewType(position: Int): Int {
        val target = findTargetAdapter(position)
        val normalPosition = normalizePosition(target, position)
        val itemViewType = target.getItemViewType(normalPosition)
        val adapterViewType = if (target === first) VIEW_TYPE_FIRST else VIEW_TYPE_SECOND
        return ViewTypeHash.hash(adapterViewType, itemViewType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewTypeHash: Int): RecyclerView.ViewHolder {
        val target: RecyclerView.Adapter<RecyclerView.ViewHolder>
                = if (ViewTypeHash.getAdapterViewType(viewTypeHash) == VIEW_TYPE_FIRST) first else second
        return target.onCreateViewHolder(parent, ViewTypeHash.getItemViewType(viewTypeHash))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val target = findTargetAdapter(position)
        val normalPosition = normalizePosition(target, position)
        target.onBindViewHolder(holder, normalPosition)
    }

    override fun getItemCount(): Int {
        return first.itemCount + second.itemCount
    }

    private fun findTargetAdapter(position: Int): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        var target: RecyclerView.Adapter<RecyclerView.ViewHolder> = first
        if (position >= first.itemCount) {
            target = second
        }
        return target
    }

    private fun normalizePosition(target: RecyclerView.Adapter<*>, position: Int): Int {
        if (target === first) {
            return position
        }
        return position - first.itemCount
    }

    private object ViewTypeHash {
        internal fun hash(adapterViewType: Int, itemViewType: Int): Int {
            return adapterViewType shl 8 or itemViewType
        }

        internal fun getAdapterViewType(hash: Int): Int {
            return hash shr 8
        }

        internal fun getItemViewType(hash: Int): Int {
            return hash and 0xFF
        }
    }

    companion object {

        private val VIEW_TYPE_FIRST = 1
        private val VIEW_TYPE_SECOND = 2
    }
}
