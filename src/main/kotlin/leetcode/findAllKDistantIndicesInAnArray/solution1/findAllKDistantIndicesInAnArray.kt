package leetcode.findAllKDistantIndicesInAnArray.solution1

import kotlin.math.min

class Solution {
    fun findKDistantIndices(nums: IntArray, key: Int, k: Int): List<Int> {
        var left = 0
        var right = min(nums.lastIndex, k)

        var keyIdx = Int.MIN_VALUE

        for (idx in left until right) {
            if (nums[idx] == key) {
                keyIdx = idx
            }
        }

        val result = mutableListOf<Int>()

        while (right < nums.size) {
            if (nums[right] == key) {
                keyIdx = right
            }

            if (keyIdx in left..right) {
                for (idx in left .. right) {
                    result.add(idx)
                }
            }

            left += 1
            right += 1
        }

        return result.distinct()
    }
}