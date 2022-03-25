package ctci.minimumWindowSubsequence.solution1

import kotlin.math.min

class Solution {

    var minLength = Int.MAX_VALUE
    var minWindowString = ""

    fun minWindow(s1: String, s2: String): String {
        val memo = MutableList(s1.length) { MutableList(s2.length) { -1 } }
        minWindowHelper(s1, s2, 0, 0, memo)
        return minWindowString
    }

    fun minWindowHelper(s1: String, s2: String, i: Int, j: Int, memo: MutableList<MutableList<Int>>): Int {
        if (j == s2.length) {
            return i
        }

        if (i == s1.length) return Int.MAX_VALUE

        if (memo[i][j] != -1) return memo[i][j]

        var minEndIdx = Int.MAX_VALUE

        if (s1[i] == s2[j]) {
            minEndIdx = minWindowHelper(s1, s2, i + 1, j + 1, memo)
        }

        minEndIdx = min(
            minWindowHelper(s1, s2, i + 1, j, memo),
            minEndIdx
        )

        if (j == 0 && minEndIdx != Int.MAX_VALUE) {
            if (minEndIdx - i <= minLength) {
                minLength = minEndIdx - i
                minWindowString = s1.substring(i, minEndIdx)
            }
        }

        memo[i][j] = minEndIdx
        return memo[i][j]
    }
}