package com.robotnec.budget.core.calculator

/**
 * @author zak zak@swingpulse.com>
 */
interface Op {

    fun displayText(): String

    fun symbol(): String

    class Divide : Op {

        override fun displayText(): String {
            return "\u00f7"
        }

        override fun symbol(): String {
            return "/"
        }
    }

    class Multiply : Op {

        override fun displayText(): String {
            return "×"
        }

        override fun symbol(): String {
            return "*"
        }
    }

    class Minus : Op {

        override fun displayText(): String {
            return "-"
        }

        override fun symbol(): String {
            return "-"
        }
    }

    class Plus : Op {

        override fun displayText(): String {
            return "+"
        }

        override fun symbol(): String {
            return "+"
        }
    }

    companion object {

        val DIVIDE: Op = Divide()
        val MULTIPLY: Op = Multiply()
        val MINUS: Op = Minus()
        val PLUS: Op = Plus()
    }
}
