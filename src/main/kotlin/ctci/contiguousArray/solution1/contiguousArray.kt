package ctci.contiguousArray.solution1

import kotlin.math.max

class Solution {
    fun findMaxLength(nums: IntArray): Int {
        val seen = mutableMapOf(0 to -1)
        var count = 0

        var maxLen = 0

        for (idx in nums.indices) {
            if (nums[idx] == 0) {
                count -= 1
            } else {
                count += 1
            }

            if (count in seen) {
                maxLen = max(idx - seen[count]!!, maxLen)
                continue
            }

            seen[count] = idx
        }

        return maxLen
    }
}