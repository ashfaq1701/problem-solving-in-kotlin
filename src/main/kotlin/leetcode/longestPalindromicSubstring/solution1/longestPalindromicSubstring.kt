package leetcode.longestPalindromicSubstring.solution1

class Solution {

    fun longestPalindrome(s: String): String {
        var longest = 0 to 0

        for (i in s.indices) {
            val oddBounds = getPalindromeBounds(s, i - 1, i + 1)
            val evenBounds = getPalindromeBounds(s, i, i + 1)

            val (oddLenStart, oddLenEnd) = oddBounds
            val (evenLenStart, evenLenEnd) = evenBounds

            val longer = if (oddLenEnd - oddLenStart > evenLenEnd - evenLenStart) oddBounds else evenBounds

            longest = if (longer.second - longer.first > longest.second - longest.first) {
                longer
            } else {
                longest
            }
        }

        return s.substring(longest.first, longest.second)
    }

    private fun getPalindromeBounds(s: String, left: Int, right: Int): Pair<Int, Int> {
        var currentLeft = left
        var currentRight = right

        while (currentLeft >= 0 && currentRight < s.length && s[currentLeft] == s[currentRight]) {
            currentLeft -= 1
            currentRight += 1
        }

        return currentLeft + 1 to currentRight
    }
}