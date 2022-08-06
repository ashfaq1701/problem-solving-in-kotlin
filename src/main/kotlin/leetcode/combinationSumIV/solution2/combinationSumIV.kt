package leetcode.combinationSumIV.solution2

class Solution {
    fun combinationSum4(nums: IntArray, target: Int): Int {
        val dp = MutableList(target + 1) { 0 }
        dp[0] = 1

        for (combSum in 1 .. target) {
            for (num in nums) {
                if (combSum - num >= 0) {
                    dp[combSum] += dp[combSum - num]
                }
            }
        }

        return dp[target]
    }
}