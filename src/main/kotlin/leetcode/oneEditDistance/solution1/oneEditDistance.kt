package leetcode.oneEditDistance.solution1

class Solution {
    fun isOneEditDistance(s: String, t: String): Boolean {
        var i = 0

        while (i < s.length && i < t.length) {
            if (s[i] != t[i]) {
                return isSameStartingAt(s, t, i + 1, i) ||
                        isSameStartingAt(s, t, i, i + 1) ||
                        isSameStartingAt(s, t, i + 1, i + 1)
            }

            i += 1
        }

        return s.length - i + t.length - i == 1
    }

    fun isSameStartingAt(s: String, t: String, i: Int, j: Int): Boolean {
        var currI = i
        var currJ = j

        while (currI < s.length && currJ < t.length) {
            if (s[currI] != t[currJ]) {
                return false
            }

            currI += 1
            currJ += 1
        }

        return currI == s.length && currJ == t.length
    }
}