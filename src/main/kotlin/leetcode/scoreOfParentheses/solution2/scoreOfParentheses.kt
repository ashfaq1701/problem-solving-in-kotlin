package leetcode.scoreOfParentheses.solution2

import kotlin.math.pow

class Solution {
    fun scoreOfParentheses(s: String): Int {
        var balance = 0
        var score = 0

        for (idx in s.indices) {
            val ch = s[idx]

            if (ch == '(') {
                balance += 1
            } else {
                balance -= 1

                if (s[idx - 1] == '(') {
                    score += 2.0.pow(balance).toInt()
                }
            }
        }

        return score
    }
}