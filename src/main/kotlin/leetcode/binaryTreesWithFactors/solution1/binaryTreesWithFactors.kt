package leetcode.binaryTreesWithFactors.solution1

class Solution {
    fun numFactoredBinaryTrees(arr: IntArray): Int {
        val MOD = 1_000_000_007
        arr.sort()

        val dp = MutableList(arr.size) { 1L }

        val indices = mutableMapOf<Int, Int>()
        for (idx in arr.indices) {
            indices[arr[idx]] = idx
        }

        for (i in arr.indices) {
            for (j in 0 until i) {
                if (arr[i] % arr[j] == 0) {
                    val right = arr[i] / arr[j]

                    if (right in indices) {
                        dp[i] += (dp[j] * dp[indices[right]!!]) % MOD
                    }
                }
            }
        }

        var ans = 0L
        for (x in dp) {
            ans += x
        }

        return (ans % MOD).toInt()
    }
}