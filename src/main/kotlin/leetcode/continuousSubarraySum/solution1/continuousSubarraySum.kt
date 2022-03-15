package leetcode.continuousSubarraySum.solution1

class Solution {
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        val prefixSums = computePrefixSum(nums)

        for (i in nums.indices) {
            for (j in 0 until i) {
                val from = if (j > 0) prefixSums[j - 1] else 0
                val to = prefixSums[i]
                val sumOfSubarray = to - from

                if (sumOfSubarray % k == 0) {
                    return true
                }
            }
        }

        return false
    }

    fun computePrefixSum(nums: IntArray): List<Int> {
        var runningSum = 0
        val prefixSums = mutableListOf<Int>()

        for (num in nums) {
            runningSum += num
            prefixSums.add(runningSum)
        }

        return prefixSums
    }
}