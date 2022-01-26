package ctci.dinnerPlateStacks.solution1

import java.util.Stack

class DinnerPlates(capacity: Int) {

    val available = sortedSetOf<Int>()
    val stacks = mutableListOf<Stack<Int>>()
    val maxCapacity = capacity

    fun push(`val`: Int) {
        if (available.isEmpty()) {
            stacks.add(Stack<Int>())
            available.add(stacks.lastIndex)
        }

        val availableIndex = available.first()
        val stackToInsert = stacks[availableIndex]
        stackToInsert.push(`val`)

        if (stackToInsert.size == maxCapacity) {
            available.remove(availableIndex)
        }
    }

    fun pop(): Int {
        if (stacks.isEmpty()) return -1

        return stacks.last().pop().also {
            while (stacks.isNotEmpty() && stacks.last().isEmpty()) {
                available.remove(stacks.lastIndex)
                stacks.removeAt(stacks.lastIndex)
            }

            if (stacks.isNotEmpty() && stacks.last().size < maxCapacity) {
                available.add(stacks.lastIndex)
            }
        }
    }

    fun popAtStack(index: Int): Int {
        if (index > stacks.lastIndex || stacks[index].isEmpty()) return -1

        if (index == stacks.lastIndex) return pop()

        val stack = stacks[index]
        return stack.pop().also {
            available.add(index)
        }
    }
}
