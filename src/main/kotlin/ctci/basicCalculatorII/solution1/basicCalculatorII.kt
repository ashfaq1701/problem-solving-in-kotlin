package ctci.basicCalculatorII.solution1

import java.util.*

class Solution {
    fun calculate(s: String): Int {
        var currentNum = 0
        val stack = Stack<Int>()
        var lastSign = '+'

        for (i in s.indices) {
            val c = s[i]

            if (c.isDigit()) {
                currentNum = currentNum * 10 + (c - '0')
            }

            if ((!c.isDigit() && c != ' ') || (i == s.lastIndex)) {
                when (lastSign) {
                    '+' -> stack.push(currentNum)
                    '-' -> stack.push(-currentNum)
                    '*' -> stack.push(stack.pop() * currentNum)
                    '/' -> stack.push(stack.pop() / currentNum)
                }

                lastSign = c
                currentNum = 0
            }
        }

        return stack.sum()
    }
}