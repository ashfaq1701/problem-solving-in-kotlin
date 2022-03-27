package leetcode.findFirstAndLastPositionOfElementInSortedArray.solution1

class Solution {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        return intArrayOf(
            searchForValue(nums, target, true),
            searchForValue(nums, target, false)
        )
    }

    private fun searchForValue(nums: IntArray, target: Int, isFirst: Boolean): Int {
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (nums[mid] < target) {
                left = mid + 1
            } else if (nums[mid] > target) {
                right = mid - 1
            } else {
                if (isFirst) {
                    if (mid == left || nums[mid - 1] != target) {
                        return mid
                    } else {
                        right = mid - 1
                    }
                } else {
                    if (mid == right || nums[mid + 1] != target) {
                        return mid
                    } else {
                        left = mid + 1
                    }
                }
            }
        }

        return -1
    }
}