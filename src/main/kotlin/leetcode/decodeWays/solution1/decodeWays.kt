package leetcode.decodeWays.solution1

class Solution {
    fun numDecodings(s: String): Int {
        val cache = mutableMapOf<Int, Int>()
        return decodeWaysHelper(0, s, cache)
    }

    private fun decodeWaysHelper(idx: Int, s: String, cache: MutableMap<Int, Int>): Int {
        if (idx == s.length) return 1

        if (idx in cache) return cache[idx]!!

        var ways = 0

        if (s[idx] != '0') {
            ways += decodeWaysHelper(idx + 1, s, cache)
        }

        if (idx < s.lastIndex) {
            val num2Digits = s.substring(idx .. idx + 1).toInt()
            if (num2Digits in 10..26) {
                ways += decodeWaysHelper(idx + 2, s, cache)
            }
        }

        cache[idx] = ways
        return cache[idx]!!
    }
}