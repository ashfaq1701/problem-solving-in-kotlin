package ctci.permutations.solution1

class Solution {
    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        permutationHelper(nums, 0, result)
        return result
    }

    fun permutationHelper(nums: IntArray, startIdx: Int, result: MutableList<List<Int>>) {
        if (startIdx == nums.size) {
            result.add(nums.toList())
        }

        for (currentIdx in startIdx .. nums.lastIndex) {
            swap(nums, currentIdx, startIdx)
            permutationHelper(nums, startIdx + 1, result)
            swap(nums, startIdx, currentIdx)
        }
    }

    fun swap(nums: IntArray, i: Int, j: Int) {
        nums[i] = nums[j].also {
            nums[j] = nums[i]
        }
    }
}
