package ctci.subsets.solution1

class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        return subsetsHelper(nums, nums.lastIndex)
    }

    fun subsetsHelper(nums: IntArray, idx: Int): List<List<Int>> {
        if (idx < 0) return listOf(listOf())

        val nextSubsets = subsetsHelper(nums, idx - 1).toMutableList()

        val currentElement = nums[idx]

        val currentSize = nextSubsets.size
        for (i in 0 until currentSize) {
            nextSubsets.add(nextSubsets[i].plus(currentElement))
        }

        return nextSubsets
    }
}