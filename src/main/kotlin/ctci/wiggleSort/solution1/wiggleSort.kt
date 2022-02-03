package ctci.wiggleSort.solution1

class Solution {
    fun wiggleSort(nums: IntArray): Unit {
        nums.sort()

        for (i in 1 until nums.lastIndex step 2) {
            nums[i] = nums[i + 1].also {
                nums[i + 1] = nums[i]
            }
        }
    }
}