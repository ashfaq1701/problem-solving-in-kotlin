package leetcode.combinationSumIV.solution1

class Solution {
    fun combinationSum4(nums: IntArray, target: Int): Int {
        val memo = mutableMapOf<Int, Int>()
        return combinationSumHelper(nums, target, memo)
    }

    private fun combinationSumHelper(nums: IntArray, remaining: Int, memo: MutableMap<Int, Int>): Int {
        if (remaining == 0) return 1

        if (remaining in memo) return memo[remaining]!!

        var result = 0

        for (num in nums) {
            if (remaining - num >= 0) {
                result += combinationSumHelper(nums, remaining - num, memo)
            }
        }

        memo[remaining] = result
        return result
    }
}