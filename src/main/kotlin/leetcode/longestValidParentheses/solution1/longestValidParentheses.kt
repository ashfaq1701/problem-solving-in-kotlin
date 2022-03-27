package leetcode.longestValidParentheses.solution1

import java.util.*
import kotlin.math.max

class Solution {
    fun longestValidParentheses(s: String): Int {
        val stack = Stack<Int>()
        stack.push(-1)

        var longest = 0

        for (idx in s.indices) {
            if (s[idx] == '(') {
                stack.push(idx)
            } else {
                stack.pop()

                if (stack.isEmpty()) {
                    stack.push(idx)
                } else {
                    longest = max(idx - stack.peek(), longest)
                }
            }
        }

        return longest
    }
}