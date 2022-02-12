package ctci.maximumSubarray.solution1

import kotlin.math.max

class Solution {
    fun maxSubArray(nums: IntArray): Int {
        var maxEndingHere = nums[0]
        var maxSoFar = nums[0]

        for (i in 1 until nums.size) {
            maxEndingHere = max(nums[i], maxEndingHere + nums[i])
            maxSoFar = max(maxEndingHere, maxSoFar)
        }

        return maxSoFar
    }
}