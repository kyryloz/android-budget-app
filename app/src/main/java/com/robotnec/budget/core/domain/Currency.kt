package com.robotnec.budget.core.domain

/**
 * @author zak zak@swingpulse.com>
 */
enum class Currency constructor(val code: String, val symbol: String) {
    UAH("UAH", "₴"), USD("USD", "$");

    companion object {

        fun fromCode(code: String): Currency {
            return values().filter { v -> v.code == code }.single()
        }

        val allCodes: List<String>
            get() = values().map { v -> v.code }.toList()
    }
}
