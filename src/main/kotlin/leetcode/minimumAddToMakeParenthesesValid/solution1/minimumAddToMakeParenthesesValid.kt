package leetcode.minimumAddToMakeParenthesesValid.solution1

import java.util.Stack

class Solution {
    fun minAddToMakeValid(s: String): Int {
        var parensToInsert = 0
        val stk = Stack<Char>()

        for (c in s) {
            if (c == '(') {
                stk.add(c)
            } else if (c == ')') {
                if (stk.isEmpty()) {
                    parensToInsert += 1
                    continue
                }

                stk.pop()
            }
        }

        parensToInsert += stk.size

        return parensToInsert
    }
}