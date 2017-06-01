package com.robotnec.budget.core.calculator

import android.text.TextUtils

import com.annimon.stream.Collectors
import com.annimon.stream.Stream

import java.util.Deque
import java.util.LinkedList
import java.util.Objects

/**
 * @author zak zak@swingpulse.com>
 */
internal class Input {

    val inputStack: Deque<Entry> = LinkedList()

    init {
        clear()
    }

    fun append(symbol: String) {
        inputStack.add(Entry(symbol, symbol, false))
    }

    fun append(op: Op) {
        inputStack.add(Entry(op.displayText(), op.symbol(), true))
    }

    fun replace(symbol: String) {
        inputStack.clear()
        inputStack.add(Entry(symbol, symbol, false))
    }

    fun delete() {
        inputStack.removeLast()
    }

    fun clear() {
        inputStack.clear()
    }

    fun toDisplayText(): String {
        val text = inputStack.map { it.displayText }.joinToString()
        return if (TextUtils.isEmpty(text)) "0" else text
    }

    fun toExpression(): String {
        return inputStack.map { it.symbol }.joinToString()
    }

    internal data class Entry(val displayText: String, val symbol: String, val isOperation: Boolean)
}
