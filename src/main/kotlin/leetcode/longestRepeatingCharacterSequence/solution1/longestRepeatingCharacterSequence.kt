package leetcode.longestRepeatingCharacterSequence.solution1

import kotlin.math.max

class Solution {
    fun characterReplacement(s: String, k: Int): Int {
        val charCounts = MutableList(26) { 0 }
        var left = 0
        var right = 0
        var maxCount = 0
        var maxLength = 0

        while (right < s.length) {

            charCounts[s[right] - 'A'] += 1
            maxCount = max(maxCount,  charCounts[s[right] - 'A'])

            while (right - left + 1 - maxCount > k) {
                charCounts[s[left] - 'A'] -= 1
                left += 1
            }

            maxLength = max(maxLength, right - left + 1)
            right += 1
        }

        return maxLength
    }
}