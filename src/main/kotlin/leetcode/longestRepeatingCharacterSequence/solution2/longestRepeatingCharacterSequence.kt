package leetcode.longestRepeatingCharacterSequence.solution2

import kotlin.math.max

class Solution {
    fun characterReplacement(s: String, k: Int): Int {
        val charCounts = MutableList(26) { 0 }
        var left = 0
        var right = 0

        var maxLen = 0

        while (right < s.length) {
            charCounts[s[right] - 'A'] += 1

            while (!containsReplaceableSequence(charCounts, right - left + 1, k)) {
                charCounts[s[left] - 'A'] -= 1
                left += 1
            }

            maxLen = max(maxLen, right - left + 1)
            right += 1
        }

        return maxLen
    }

    fun containsReplaceableSequence(charCounts: List<Int>, len: Int, k: Int): Boolean {
        for (count in charCounts) {
            if (len - count <= k) {
                return true
            }
        }

        return false
    }
}