package ctci.palindromePermutation.solution1

class Solution {
    fun canPermutePalindrome(s: String): Boolean {
        val charCounts = MutableList(26) { 0 }

        for (c in s) {
            charCounts[c - 'a'] += 1
        }

        val oddCharCounts = charCounts.count { it % 2 == 1 }

        return oddCharCounts <= 1
    }
}