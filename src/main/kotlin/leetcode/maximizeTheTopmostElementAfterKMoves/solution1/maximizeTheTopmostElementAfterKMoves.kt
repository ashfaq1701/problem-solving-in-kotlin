package leetcode.maximizeTheTopmostElementAfterKMoves.solution1

import kotlin.math.min
import kotlin.math.max

class Solution {
    fun maximumTop(nums: IntArray, k: Int): Int {

        if (nums.size == 1) return if (k == 0 || k % 2 == 0) nums[0] else -1

        if (k == 1) return if (nums.size >= 2) nums[1] else -1

        var maxElement = Int.MIN_VALUE
        val rightElement = min(k, nums.lastIndex)

        for (i in 0 .. rightElement) {
            if (i == k - 1) continue

            maxElement = max(maxElement, nums[i])
        }

        return maxElement
    }
}