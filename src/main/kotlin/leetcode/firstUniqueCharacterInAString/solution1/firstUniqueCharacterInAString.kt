package leetcode.firstUniqueCharacterInAString.solution1

class Solution {
    fun firstUniqChar(s: String): Int {
        val charCounts = mutableMapOf<Char, Int>()

        for (ch in s) {
            if (ch !in charCounts) {
                charCounts[ch] = 0
            }

            charCounts[ch] = charCounts[ch]!! + 1
        }

        for (i in s.indices) {
            if (charCounts[s[i]]!! == 1) {
                return i
            }
        }

        return -1
    }
}