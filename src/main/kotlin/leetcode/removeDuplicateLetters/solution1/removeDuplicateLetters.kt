package leetcode.removeDuplicateLetters.solution1

import java.util.Stack

class Solution {
    fun removeDuplicateLetters(s: String): String {
        val seen = mutableSetOf<Char>()
        val lastSeenAt = mutableMapOf<Char, Int>()
        val stk = Stack<Char>()

        for (idx in s.indices) {
            lastSeenAt[s[idx]] = idx
        }

        for (idx in s.indices) {
            val ch = s[idx]

            if (ch !in seen) {

                while (stk.isNotEmpty() && ch < stk.peek() && lastSeenAt[stk.peek()]!! > idx) {
                    val popped = stk.pop()
                    seen.remove(popped)
                }

                seen.add(ch)
                stk.push(ch)
            }
        }

        return String(stk.toList().toCharArray())
    }
}
