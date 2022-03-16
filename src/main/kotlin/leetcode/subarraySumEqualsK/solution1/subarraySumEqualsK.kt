package leetcode.subarraySumEqualsK.solution1

class Solution {
    fun subarraySum(nums: IntArray, k: Int): Int {
        val seenCounts = mutableMapOf<Int, Int>()
        seenCounts[0] = 1

        var count = 0

        var runningSum = 0
        for (num in nums) {
            runningSum += num
            val diff = runningSum - k

            if (diff in seenCounts) {
                count += seenCounts[diff]!!
            }

            if (runningSum !in seenCounts) {
                seenCounts[runningSum] = 0
            }
            seenCounts[runningSum] = seenCounts[runningSum]!! + 1
        }

        return count
    }
}