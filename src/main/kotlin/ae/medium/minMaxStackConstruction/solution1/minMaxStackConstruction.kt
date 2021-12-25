package ae.medium.minMaxStackConstruction.solution1

import kotlin.math.min
import kotlin.math.max

open class MinMaxStack() {
    val stack = mutableListOf<Int>()
    val minMaxStack = mutableListOf<MinMax>()

    fun peek(): Int? {
        return if (stack.isNotEmpty()) stack.last() else null
    }

    fun pop(): Int? {
        return if (stack.isNotEmpty()) {
            minMaxStack.removeAt(minMaxStack.lastIndex)
            stack.removeAt(stack.lastIndex)
        } else null
    }

    fun push(number: Int) {
        val minElement = if (minMaxStack.isNotEmpty()) {
            val lastMinElement = minMaxStack.last().min
            min(lastMinElement, number)
        } else number

        val maxElement = if (minMaxStack.isNotEmpty()) {
            val lastMaxElement = minMaxStack.last().max
            max(lastMaxElement, number)
        } else number

        minMaxStack.add(MinMax(minElement, maxElement))
        stack.add(number)
    }

    fun getMin(): Int? {
        return if (stack.isNotEmpty()) {
            minMaxStack.last().min
        } else null
    }

    fun getMax(): Int? {
        return if (stack.isNotEmpty()) {
            minMaxStack.last().max
        } else null
    }
}

data class MinMax(val min: Int, val max: Int)
