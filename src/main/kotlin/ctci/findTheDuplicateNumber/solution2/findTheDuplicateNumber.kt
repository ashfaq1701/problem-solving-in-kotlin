package ctci.findTheDuplicateNumber.solution2

class Solution {
    fun findDuplicate(nums: IntArray): Int {
        return store(nums, 0)
    }

    fun store(nums: IntArray, cur: Int): Int {
        if (nums[cur] == cur) {
            return cur
        }

        val temp = nums[cur]
        nums[cur] = cur
        return store(nums, temp)
    }
}