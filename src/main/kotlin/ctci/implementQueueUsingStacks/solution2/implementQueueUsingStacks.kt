package ctci.implementQueueUsingStacks.solution2

import java.util.Stack

class MyQueue() {

    val primaryStack = Stack<Int>()
    val secondaryStack = Stack<Int>()

    fun push(x: Int) {
        primaryStack.push(x)
    }

    fun pop(): Int {
        if (secondaryStack.isEmpty()) {
            refillSecondaryStack()
        }

        return secondaryStack.pop()
    }

    fun peek(): Int {
        if (secondaryStack.isEmpty()) {
            refillSecondaryStack()
        }

        return secondaryStack.peek()
    }

    fun empty(): Boolean {
        return primaryStack.isEmpty() && secondaryStack.isEmpty()
    }

    private fun refillSecondaryStack() {
        while (primaryStack.isNotEmpty()) {
            secondaryStack.push(primaryStack.pop())
        }
    }

}