package leetcode.reorderedPowerOfTwo.solution1

class Solution {
    fun reorderedPowerOf2(n: Int): Boolean {
        val numsArray = n.toString().map { it - '0' }.toIntArray()
        return permutationHelper(numsArray, 0)
    }

    fun permutationHelper(nums: IntArray, startIdx: Int): Boolean {
        if (startIdx == nums.size) {
            return isPowerOfTwo(nums);
        }

        for (currentIdx in startIdx .. nums.lastIndex) {
            swap(nums, currentIdx, startIdx)
            if (permutationHelper(nums, startIdx + 1)) {
                return true
            }
            swap(nums, startIdx, currentIdx)
        }

        return false
    }

    fun swap(nums: IntArray, i: Int, j: Int) {
        nums[i] = nums[j].also {
            nums[j] = nums[i]
        }
    }

    fun isPowerOfTwo(nums: IntArray): Boolean {
        if(nums[0] == 0) return false

        var num = 0

        for (n in nums) {
            num = num * 10 + n
        }

        while (num > 0 && (num % 2 == 0)) {
            num = num / 2
        }

        return num == 1
    }
}