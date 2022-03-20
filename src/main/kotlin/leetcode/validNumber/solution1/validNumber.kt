package leetcode.validNumber.solution1

class Solution {
    fun isNumber(s: String): Boolean {
        var seenDigit = false
        var seenExponent = false
        var seenDot = false

        for (i in s.indices) {
            val ch = s[i]

            when {
                ch.isDigit() -> {
                    seenDigit = true
                }
                ch == '+' || ch == '-' -> {
                    if (i > 0 && s[i - 1] != 'e' && s[i - 1] != 'E') {
                        return false
                    }
                }
                ch == 'e' || ch == 'E' -> {
                    if (seenExponent || !seenDigit) {
                        return false
                    }
                    seenExponent = true
                    // After e, we have to have some numerical part.
                    // setting seenDigit as false will help with this return value
                    // in the last line
                    seenDigit = false
                }
                ch == '.' -> {
                    if (seenDot || seenExponent) {
                        return false
                    }
                    seenDot = true
                }
                else -> {
                    return false
                }
            }
        }

        return seenDigit
    }
}