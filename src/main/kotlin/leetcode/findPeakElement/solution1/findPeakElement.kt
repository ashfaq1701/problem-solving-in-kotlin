package leetcode.findPeakElement.solution1

class Solution {
    fun findPeakElement(nums: IntArray): Int {
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2
            val midElement = nums[mid]

            val elementAtLeft = if (mid > 0) nums[mid - 1] else Int.MIN_VALUE
            val elementAtRight = if (mid < nums.lastIndex) nums[mid + 1] else Int.MIN_VALUE

            if (midElement > elementAtLeft && midElement > elementAtRight) {
                return mid
            }

            if (midElement < elementAtRight) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return 0
    }
}