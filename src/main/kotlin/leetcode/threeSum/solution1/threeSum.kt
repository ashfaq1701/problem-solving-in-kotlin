package leetcode.threeSum.solution1

class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()

        val result = mutableListOf<List<Int>>()

        var currentIdx = 0
        while (currentIdx < nums.size) {
            val twoSumResults = twoSum(nums, currentIdx + 1, -nums[currentIdx])
            for (twoSumResult in twoSumResults) {
                result.add(listOf(nums[currentIdx]).plus(twoSumResult))
            }

            currentIdx += 1

            while (currentIdx < nums.size && nums[currentIdx] == nums[currentIdx - 1]) {
                currentIdx += 1
            }
        }

        return result
    }

    private fun twoSum(nums: IntArray, startIdx: Int, k: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        var left = startIdx
        var right = nums.lastIndex

        while (left < right) {
            if (nums[left] + nums[right] == k) {
                result.add(listOf(nums[left], nums[right]))
                left += 1
                right -= 1

                while (left < nums.size && nums[left] == nums[left - 1]) {
                    left += 1
                }

                while (right >= 0 && nums[right] == nums[right + 1]) {
                    right -= 1
                }

            } else if (nums[left] + nums[right] < k) {
                left += 1
            } else {
                right -= 1
            }
        }

        return result
    }
}