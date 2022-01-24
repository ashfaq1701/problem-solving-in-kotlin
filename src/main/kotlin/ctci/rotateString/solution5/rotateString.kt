package ctci.rotateString.solution5

class Solution {
    fun rotateString(s: String, goal: String): Boolean {
        if (s.length != goal.length) return false

        if (s.isEmpty()) return true

        val pattern = buildPattern(s + s)

        return contains(s + s, goal, pattern)
    }

    fun buildPattern(s: String): List<Int> {
        val pattern = MutableList(s.length) { -1 }

        var i = 1
        var j = 0

        while (i < s.length) {
            if (s[i] == s[j]) {
                pattern[i] = j
                i += 1
                j += 1
            } else if (j > 0) {
                j = pattern[j - 1] + 1
            } else {
                i += 1
            }
        }

        return pattern
    }

    fun contains(s: String, goal: String, pattern: List<Int>): Boolean {
        var i = 0
        var j = 0

        // More easily but a bit less optimally while (i < s.length) {} will work too.
        while (i + goal.length - j <= s.length) {
            if (s[i] == goal[j]) {
                if (j == goal.lastIndex) {
                    return true
                }

                i += 1
                j += 1
            } else if (j > 0) {
                j = pattern[j - 1] + 1
            } else {
                i += 1
            }
        }

        return false
    }
}