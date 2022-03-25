package ctci.houseRobber.solution1

import kotlin.math.max

class Solution {
    fun rob(nums: IntArray): Int {
        val memo = MutableList<Int>(nums.size) { -1 }
        return max(
            robHelper(0, nums, memo),
            robHelper(1, nums, memo)
        )
    }

    fun robHelper(idx: Int, nums: IntArray, memo: MutableList<Int>): Int {
        if (idx >= nums.size) return 0

        if (memo[idx] != -1) return memo[idx]

        memo[idx] = max(
            robHelper(idx + 1, nums, memo),
            nums[idx] + robHelper(idx + 2, nums, memo)
        )

        return memo[idx]
    }
}