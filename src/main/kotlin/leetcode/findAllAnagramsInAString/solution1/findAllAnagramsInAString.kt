package leetcode.findAllAnagramsInAString.solution1

class Solution {
    fun findAnagrams(s: String, p: String): List<Int> {
        if (s.length < p.length) return listOf()

        val startIndices = mutableListOf<Int>()

        val pCharCounts = getCharCounts(p, p.length)
        val sCharCounts = getCharCounts(s, p.length - 1)

        for (idx in p.lastIndex until s.length) {
            val ch = s[idx]
            sCharCounts[ch - 'a'] += 1

            val startIdx = idx - p.length + 1
            if (compareCharCounts(sCharCounts, pCharCounts)) {
                startIndices.add(startIdx)
            }

            val leftChar = s[startIdx]
            sCharCounts[leftChar - 'a'] -= 1
        }

        return startIndices
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