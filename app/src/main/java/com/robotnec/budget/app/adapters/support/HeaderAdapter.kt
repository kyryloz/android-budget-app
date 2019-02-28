package com.robotnec.budget.app.adapters.support

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

/**
 * @author zak zak@swingpulse.com>
 */
class HeaderAdapter(private val body: RecyclerView.Adapter<RecyclerView.ViewHolder>,
                    private val listHeader: ListHeader) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return if (position == 0) {
            -1
        } else {
            body.getItemId(position - 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ViewTypeHash.hash(VIEW_TYPE_HEADER, 0)
        } else {
            ViewTypeHash.hash(VIEW_TYPE_BODY, body.getItemViewType(position - 1))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewTypeHash: Int): RecyclerView.ViewHolder {
        return if (ViewTypeHash.getAdapterViewType(viewTypeHash) == VIEW_TYPE_HEADER) {
            listHeader.onCreateViewHolder(parent)
        } else {
            body.onCreateViewHolder(parent, ViewTypeHash.getItemViewType(viewTypeHash))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            listHeader.onBindViewHolder(holder)
        } else {
            body.onBindViewHolder(holder, position - 1)
        }
    }

    override fun getItemCount(): Int {
        return body.itemCount + 1
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
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_BODY = 2
    }
}
