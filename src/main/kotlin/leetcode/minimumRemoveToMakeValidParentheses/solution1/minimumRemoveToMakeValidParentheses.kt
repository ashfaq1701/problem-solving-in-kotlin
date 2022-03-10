package leetcode.minimumRemoveToMakeValidParentheses.solution1

import java.util.Stack

class Solution {
    fun minRemoveToMakeValid(s: String): String {
        val idxsToRemove = mutableSetOf<Int>()
        val stk = Stack<Int>()

        for (i in s.indices) {
            if (s[i] == '(') {
                stk.add(i)
            } else if (s[i] == ')') {
                if (stk.isEmpty()) {
                    idxsToRemove.add(i)
                } else {
                    stk.pop()
                }
            }
        }

        while (stk.isNotEmpty()) {
            idxsToRemove.add(stk.pop())
        }

        val resultSB = StringBuilder()
        for (i in s.indices) {
            if (i !in idxsToRemove) {
                resultSB.append(s[i])
            }
        }

        return resultSB.toString()
    }
}