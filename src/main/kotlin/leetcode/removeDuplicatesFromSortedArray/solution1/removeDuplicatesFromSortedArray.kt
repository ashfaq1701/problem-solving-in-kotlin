package leetcode.removeDuplicatesFromSortedArray.solution1

class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        var slow = 0

        for (fast in 0 until nums.size) {

            if (nums[fast] == nums[slow]) continue

            slow += 1
            nums[slow] = nums[fast]
        }

        return slow + 1
    }
}