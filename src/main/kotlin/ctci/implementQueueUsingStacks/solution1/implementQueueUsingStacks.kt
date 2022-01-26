package ctci.implementQueueUsingStacks.solution1

import java.util.Stack

class MyQueue() {

    private val primaryStack = Stack<Int>()
    private val secondaryStack = Stack<Int>()

    fun push(x: Int) {
        while (primaryStack.isNotEmpty()) {
            secondaryStack.push(primaryStack.pop())
        }

        primaryStack.push(x)

        while (secondaryStack.isNotEmpty()) {
            primaryStack.push(secondaryStack.pop())
        }
    }

    fun pop(): Int {
        return primaryStack.pop()
    }

    fun peek(): Int {
        return primaryStack.peek()
    }

    fun empty(): Boolean {
        return primaryStack.isEmpty()
    }
}