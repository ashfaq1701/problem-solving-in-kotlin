package ctci.permutationInString.solution2

class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        val s1Freq = MutableList(26) { 0 }
        for (c in s1) {
            s1Freq[c - 'a'] += 1
        }

        val s2Freq = MutableList(26) { 0 }

        var left = 0
        var right = 0

        while (right < s2.length) {
            val rightCharCode = s2[right] - 'a'
            s2Freq[rightCharCode] += 1

            if (right - left + 1 > s1.length) {
                val leftCharCode = s2[left] - 'a'
                s2Freq[leftCharCode] -= 1
                left += 1
            }

            if (s2Freq == s1Freq) return true

            right += 1
        }

        return false
    }
}