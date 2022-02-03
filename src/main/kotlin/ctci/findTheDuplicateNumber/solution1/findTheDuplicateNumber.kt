package ctci.findTheDuplicateNumber.solution1

import kotlin.math.abs

class Solution {
    fun findDuplicate(nums: IntArray): Int {
        for (n in nums) {
            val mappedIdx = abs(n) - 1
            if (nums[mappedIdx] < 0) {
                return abs(n)
            }
            nums[mappedIdx] = -nums[mappedIdx]
        }
        return -1
    }
}