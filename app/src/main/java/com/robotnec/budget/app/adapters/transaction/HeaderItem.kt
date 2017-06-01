package com.robotnec.budget.app.adapters.transaction

import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robotnec.budget.R
import com.robotnec.budget.app.util.DateUtil
import com.robotnec.budget.core.domain.MoneyAmount
import kotlinx.android.synthetic.main.item_transaction_header.view.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.TextStyle
import java.util.*

/**
 * @author zak zak@swingpulse.com>
 */
internal class HeaderItem(private val date: LocalDate, private val sum: MoneyAmount) : TransactionListItem {

    override val viewType: Int = TransactionsAdapter.VIEW_TYPE_HEADER

    override val id: Long = -date.toEpochDay()

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        with(viewHolder.itemView) {
            textDayOfMonth.text = dayOfMonth
            textRelativeDay.text = relativeDate
            textMonth.text = month
            textSum.text = getSum()
        }
    }

    private val relativeDate: String
        get() {
            val twoDaysAgo = Instant.now().atOffset(ZoneOffset.UTC).minusDays(2).toLocalDate()
            if (date.isAfter(twoDaysAgo)) {
                val time = DateUtil.toSeconds(date.atStartOfDay()) * 1000
                return DateUtils.getRelativeTimeSpanString(time,
                        Instant.now().toEpochMilli(),
                        DateUtils.DAY_IN_MILLIS).toString()
            } else {
                return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
            }
        }

    private val dayOfMonth: String
        get() = date.dayOfMonth.toString()

    private val month: String
        get() = date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())

    private fun getSum(): String {
        return "-" + sum.toDisplayableString()
    }

    internal class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {

        fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
            val itemView = inflater.inflate(R.layout.item_transaction_header, parent, false)
            return HeaderViewHolder(itemView)
        }
    }
}
