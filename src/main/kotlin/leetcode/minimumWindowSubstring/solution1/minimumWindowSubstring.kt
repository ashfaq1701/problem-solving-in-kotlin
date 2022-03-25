package leetcode.minimumWindowSubstring.solution1

class Solution {
    fun minWindow(s: String, t: String): String {
        var result = Int.MAX_VALUE to ""

        var left = 0
        var right = 0

        val sCounts = mutableMapOf<Char, Int>()
        val tCounts = getCharCounts(t)
        val requiredMatches = tCounts.size

        var currentMatchCount = 0

        while (right < s.length) {

            val rightChar = s[right]
            if (rightChar in tCounts) {
                addToCounts(rightChar, sCounts)
                if (sCounts[rightChar] == tCounts[rightChar]) {
                    currentMatchCount += 1
                }
            }

            while (left <= right && currentMatchCount == requiredMatches) {
                val length = right - left + 1
                val subStr = s.substring(left .. right)
                if (length < result.first) {
                    result = length to subStr
                }

                val leftChar = s[left]
                if (leftChar in tCounts && sCounts[leftChar] == tCounts[leftChar]) {
                    currentMatchCount -= 1
                }
                removeFromCounts(leftChar, sCounts)

                left += 1
            }

            right += 1
        }

        return result.second
    }

    private fun getCharCounts(str: String): Map<Char, Int> {
        val charCounts = mutableMapOf<Char, Int>()

        for (ch in str) {
            addToCounts(ch, charCounts)
        }

        return charCounts
    }

    private fun addToCounts(ch: Char, counts: MutableMap<Char, Int>) {
        if (ch !in counts) {
            counts[ch] = 0
        }

        counts[ch] = counts[ch]!! + 1
    }

    private fun removeFromCounts(ch: Char, counts: MutableMap<Char, Int>) {
        if (ch in counts && counts[ch]!! >  0) {
            counts[ch] = counts[ch]!! - 1
        }

        if (ch in counts && counts[ch]!! == 0) {
            counts.remove(ch)
        }
    }
}