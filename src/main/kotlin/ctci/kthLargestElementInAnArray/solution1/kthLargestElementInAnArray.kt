package ctci.kthLargestElementInAnArray.solution1

class Solution {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val kFromEnd = nums.size - k
        return quickSelect(nums, kFromEnd, 0, nums.lastIndex)
    }

    fun quickSelect(nums: IntArray, k: Int, start: Int, end: Int): Int {
        if (start > end) throw Exception("Invalid State")

        val pivot = start
        var left = start + 1
        var right = end

        while (left <= right) {
            if (nums[left] > nums[pivot] && nums[right] < nums[pivot]) {
                swap(nums, left, right)
            }

            if (nums[left] <= nums[pivot]) {
                left += 1
            }

            if (nums[right] >= nums[pivot]) {
                right -= 1
            }
        }

        swap(nums, pivot, right)

        return when {
            right == k -> nums[right]
            right < k -> quickSelect(nums, k, right + 1, end)
            else -> quickSelect(nums, k, start, right - 1)
        }
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        nums[i] = nums[j].also {
            nums[j] = nums[i]
        }
    }
}