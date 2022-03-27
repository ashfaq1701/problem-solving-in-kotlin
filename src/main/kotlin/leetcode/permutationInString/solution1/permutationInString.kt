package leetcode.permutationInString.solution1

class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false

        val s1Counts = getCharCounts(s1, s1.length)
        val s2Counts = getCharCounts(s2, s1.lastIndex)

        for (idx in s1.lastIndex until s2.length) {
            val ch = s2[idx]
            s2Counts[ch - 'a'] += 1

            if (compareCharCounts(s1Counts, s2Counts)) {
                return true
            }

            val startIdx = idx - s1.length + 1
            val leftChar = s2[startIdx]
            s2Counts[leftChar - 'a'] -= 1
        }

        return false
    }

    private fun getCharCounts(str: String, numChars: Int): MutableList<Int> {
        val charCounts = MutableList(26) { 0 }

        for (i in 0 until numChars) {
            val ch = str[i]
            charCounts[ch - 'a'] += 1
        }

        return charCounts
    }

    private fun compareCharCounts(counts1: List<Int>, counts2: List<Int>): Boolean {
        for (i in 0 until 26) {
            if (counts1[i] != counts2[i]) {
                return false
            }
        }

        return true
    }
}