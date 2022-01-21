package ctci.editDistance.solution2

class Solution {
    fun minDistance(word1: String, word2: String): Int {
        val dp = MutableList(word1.length + 1) { MutableList(word2.length + 1) { 0 } }

        for (r in 1 .. word1.length) {
            dp[r][0] = r
        }

        for (c in 1 .. word2.length) {
            dp[0][c] = c
        }

        for (r in 1 .. word1.length) {
            for (c in 1 .. word2.length) {
                if (word1[r - 1] == word2[c - 1]) {
                    dp[r][c] = dp[r - 1][c - 1]
                } else {
                    dp[r][c] = 1 + listOf(
                        dp[r - 1][c],
                        dp[r][c - 1],
                        dp[r - 1][c - 1]
                    ).minOrNull()!!
                }
            }
        }

        return dp[word1.length][word2.length]
    }
}