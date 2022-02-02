package ctci.searchInRotatedSortedArray.solution1

class Solution {
    fun search(nums: IntArray, target: Int): Int {
        return searchHelper(nums, target, 0, nums.lastIndex)
    }

    fun searchHelper(nums: IntArray, target: Int, start: Int, end: Int): Int {

        if (end < start) return -1

        val mid = start + (end - start) / 2

        return if (nums[mid] == target) {
            mid
        } else if (nums[mid] >= nums[start]) {
            if (target in nums[start] .. nums[mid]) {
                searchHelper(nums, target, start, mid - 1)
            } else {
                searchHelper(nums, target, mid + 1, end)
            }
        } else {
            if (target in nums[mid] .. nums[end]) {
                searchHelper(nums, target, mid + 1, end)
            } else {
                searchHelper(nums, target, start, mid - 1)
            }
        }
    }
}