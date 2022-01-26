package ctci.minStack.solution1

import kotlin.math.min

class MinStack() {

    private val stk = mutableListOf<Int>()
    private val minElements = mutableListOf<Int>()

    fun push(`val`: Int) {
        minElements.add(
            if (minElements.isEmpty()) `val` else min(`val`, minElements.last())
        )

        stk.add(`val`)
    }

    fun pop() {
        stk.removeAt(stk.lastIndex)
        minElements.removeAt(minElements.lastIndex)
    }

    fun top(): Int {
        return stk.last()
    }

    fun getMin(): Int {
        return minElements.last()
    }

}