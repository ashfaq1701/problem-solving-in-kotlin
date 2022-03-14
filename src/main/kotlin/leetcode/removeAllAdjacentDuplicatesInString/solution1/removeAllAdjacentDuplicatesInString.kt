package leetcode.removeAllAdjacentDuplicatesInString.solution1

import java.util.*

class Solution {
    fun removeDuplicates(s: String): String {
        val stack = Stack<Char>()

        for (ch in s) {
            if (stack.isNotEmpty() && stack.peek() == ch) {
                stack.pop()
            } else {
                stack.push(ch)
            }
        }

        return String(stack.toList().toCharArray())
    }
}