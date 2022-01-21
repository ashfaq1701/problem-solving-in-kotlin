package ctci.oneEditDistance.solution1

import kotlin.math.min

class Solution {
    fun isOneEditDistance(s: String, t: String): Boolean {
        var i = 0

        while (i < min(s.length, t.length) && s[i] == t[i]) {
            i += 1
        }

        if (i == s.length && i == t.length) return false

        return areSameStartingAt(s, t, i + 1, i) || areSameStartingAt(s, t, i, i + 1) || areSameStartingAt(s, t, i + 1, i + 1)
    }

    fun areSameStartingAt(s: String, t: String, sIdx: Int, tIdx: Int): Boolean {
        if (s.length - sIdx != t.length - tIdx) return false

        // s.length = 7, sIdx = 3
        // 7 - 3 = 4
        // 0 until 4
        // 0 1 2 3
        // sIdx + i
        // 3 4 5 6
        for (i in 0 until s.length - sIdx) {
            if (s[sIdx + i] != t[tIdx + i]) {
                return false
            }
        }

        return true
    }
}