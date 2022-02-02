package ctci.searchInRotatedSortedArray.solution2

class Solution {
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = left + (right - left) / 2

            if (nums[mid] == target) {
                return mid
            } else if (nums[mid] >= nums[left]) {
                if (target in nums[left] .. nums[mid]) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            } else {
                if (target in nums[mid] .. nums[right]) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
        }

        return -1
    }
}