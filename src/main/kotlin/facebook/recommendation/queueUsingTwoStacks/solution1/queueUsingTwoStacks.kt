package facebook.recommendation.queueUsingTwoStacks.solution1

import java.util.Stack

fun main(args: Array<String>) {
    val queue = QueueWithTwoStacks()

    val n = readLine()!!.toInt()

    for (i in 0 until n) {
        val operation = readLine()!!.split(" ").map { it.toInt() }

        when (operation[0]) {
            1 -> {
                val num = operation[1]
                queue.push(num)
            }
            2 -> queue.poll()
            3 -> println(queue.peek())
        }
    }
}

class QueueWithTwoStacks {
    val stk1 = Stack<Int>()
    val stk2 = Stack<Int>()

    fun push(n: Int) {
        stk1.add(n)
    }

    fun poll(): Int {
        if (stk2.isEmpty()) {
            refillStack2()
        }

        if (stk2.isEmpty()) return -1

        return stk2.pop()
    }

    fun peek(): Int {
        if (stk2.isEmpty()) {
            refillStack2()
        }

        if (stk2.isEmpty()) return -1

        return stk2.peek()
    }

    private fun refillStack2() {
        while (stk1.isNotEmpty()) {
            stk2.add(stk1.pop())
        }
    }
}