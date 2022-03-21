package leetcode.moveZeroes.solution1

class Solution {
    fun moveZeroes(nums: IntArray): Unit {
        var slow = 0

        for (i in nums.indices) {
            if (nums[i] != 0) {
                nums[slow] = nums[i]
                slow += 1
            }
        }

        for (i in slow .. nums.lastIndex) {
            nums[i] = 0
        }
    }
}