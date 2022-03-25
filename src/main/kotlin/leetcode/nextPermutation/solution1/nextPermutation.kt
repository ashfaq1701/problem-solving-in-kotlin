package leetcode.nextPermutation.solution1

class Solution {
    fun nextPermutation(nums: IntArray): Unit {
        var i = nums.lastIndex - 1

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i -= 1
        }

        if (i >= 0) {
            var j = nums.lastIndex

            while (nums[j] <= nums[i]) {
                j -= 1
            }

            swap(nums, i, j)
        }

        reverse(nums, i + 1, nums.lastIndex)
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        nums[i] = nums[j].also {
            nums[j] = nums[i]
        }
    }

    private fun reverse(nums: IntArray, i: Int, j: Int) {
        var currentI = i
        var currentJ = j

        while (currentI < currentJ) {
            swap(nums, currentI, currentJ)
            currentI += 1
            currentJ -= 1
        }
    }
}