package ctci.wiggleSort.solution2

class Solution {
    fun wiggleSort(nums: IntArray): Unit {
        var less = true

        for (i in 0 until nums.lastIndex) {
            if (less) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1)
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1)
                }
            }

            less = !less
        }
    }

    fun swap(nums: IntArray, i: Int, j: Int) {
        nums[i] = nums[j].also {
            nums[j] = nums[i]
        }
    }
}