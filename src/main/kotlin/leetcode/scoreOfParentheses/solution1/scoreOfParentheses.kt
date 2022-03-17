package leetcode.scoreOfParentheses.solution1

import java.util.*
import kotlin.math.max

class Solution {
    fun scoreOfParentheses(s: String): Int {
        val stack = Stack<Int>()
        stack.push(0)

        for (ch in s) {
            if (ch == '(') {
                stack.push(0)
            } else {
                val a = stack.pop()
                val b = stack.pop()

                stack.push(b + max(a * 2, 1))
            }
        }

        return stack.pop()
    }
}