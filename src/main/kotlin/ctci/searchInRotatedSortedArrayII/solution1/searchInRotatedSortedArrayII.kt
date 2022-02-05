package ctci.searchInRotatedSortedArrayII.solution1

class Solution {
    fun search(nums: IntArray, target: Int): Boolean {
        var start = 0
        var end = nums.lastIndex

        while (start <= end) {
            val mid = (start + end) / 2

            if (nums[mid] == target) {
                return true
            } else if (nums[mid] == nums[start]) {
                // If mid element and start elements are equal
                // Then mid can be both in first or second half
                // This is a undeterministic condition
                // Search window will start from `start + 1` now
                start += 1
            } else if (nums[mid] > nums[start]) {
                if (target in nums[start] .. nums[mid]) {
                    end = mid - 1
                } else {
                    start = mid + 1
                }
            } else {
                if (target in nums[mid] .. nums[end]) {
                    start = mid + 1
                } else {
                    end = mid - 1
                }
            }
        }

        return false
    }
}