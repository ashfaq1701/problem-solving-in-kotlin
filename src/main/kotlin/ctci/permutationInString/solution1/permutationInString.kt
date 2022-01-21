package ctci.permutationInString.solution1

class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false

        val s1Freq = MutableList(26) { 0 }
        for (c in s1) {
            s1Freq[c - 'a'] += 1
        }

        val s2Freq = MutableList(26) { 0 }

        for (i in 0 until s1.length) {
            s2Freq[s2[i] - 'a'] += 1
        }

        if (s1Freq == s2Freq) {
            return true
        }

        for (startIdx in 1 .. s2.length - s1.length) {
            val prevCharIdx = s2[startIdx - 1] - 'a'
            val windowEndCharIdx = s2[startIdx + s1.length - 1] - 'a'

            s2Freq[prevCharIdx] -= 1
            s2Freq[windowEndCharIdx] += 1

            if (s1Freq == s2Freq) {
                return true
            }
        }

        return false
    }
}