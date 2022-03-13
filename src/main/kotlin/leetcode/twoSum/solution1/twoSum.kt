package leetcode.twoSum.solution1

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val seen = mutableMapOf<Int, Int>()

        for (idx in nums.indices) {
            val num = nums[idx]

            if ((target - num) in seen) {
                return listOf(seen[target - num]!!, idx).toIntArray()
            }

            seen[num] = idx
        }

        return intArrayOf()
    }
}