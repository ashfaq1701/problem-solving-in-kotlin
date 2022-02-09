package ctci.shortestUnsortedContinuousSubarray.solution1

import kotlin.math.min
import kotlin.math.max

class Solution {
    fun findUnsortedSubarray(nums: IntArray): Int {
        val (minUnsorted, maxUnsorted) = getMinMaxUnsortedNumbers(nums)

        if (minUnsorted == Int.MAX_VALUE) return 0

        var sortedPositionOfMinUnsorted = 0
        while (nums[sortedPositionOfMinUnsorted] <= minUnsorted) {
            sortedPositionOfMinUnsorted += 1
        }

        var sortedPositionOfMaxUnsorted = nums.lastIndex
        while (nums[sortedPositionOfMaxUnsorted] >= maxUnsorted) {
            sortedPositionOfMaxUnsorted -= 1
        }

        return sortedPositionOfMaxUnsorted - sortedPositionOfMinUnsorted + 1
    }

    fun getMinMaxUnsortedNumbers(nums: IntArray): Pair<Int, Int> {
        var maxUnsorted = Int.MIN_VALUE
        var minUnsorted = Int.MAX_VALUE

        for (i in nums.indices) {
            if (isUnsorted(nums, i)) {
                maxUnsorted = max(maxUnsorted, nums[i])
                minUnsorted = min(minUnsorted, nums[i])
            }
        }

        return minUnsorted to maxUnsorted
    }

    fun isUnsorted(nums: IntArray, idx: Int): Boolean {
        return if (idx == 0) {
            idx + 1 < nums.size && nums[idx] > nums[idx + 1]
        } else if (idx == nums.lastIndex) {
            idx - 1 >= 0 && nums[idx] < nums[idx - 1]
        } else {
            (idx - 1 >= 0 && nums[idx] < nums[idx - 1]) ||
                    (idx + 1 < nums.size && nums[idx] > nums[idx + 1])
        }
    }
}