package leetcode.continuousSubarraySum.solution2

class Solution {
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        var currentSum = 0
        val seen = mutableMapOf<Int, Int>()
        seen[currentSum] = -1

        for (idx in nums.indices) {
            val num = nums[idx]
            currentSum += num
            val rem = currentSum % k

            if (rem in seen) {
                if (idx - seen[rem]!! >= 2) {
                    return true
                }
                continue
            }

            seen[rem] = idx
        }

        return false
    }
}