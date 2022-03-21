package leetcode.longestSubstringWithoutRepeatingCharacters.solution1

import kotlin.math.max

class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var left = 0
        var right = 0

        val freq = mutableMapOf<Char, Int>()

        var len = Int.MIN_VALUE

        while (right < s.length) {
            if (s[right] !in freq) {
                freq[s[right]] = 0
            }
            freq[s[right]] = freq[s[right]]!! + 1

            while (freq[s[right]]!! > 1) {
                freq[s[left]] = freq[s[left]]!! - 1
                left += 1
            }

            len = max(right - left + 1, len)

            right += 1
        }

        return if (len == Int.MIN_VALUE) 0 else len
    }
}