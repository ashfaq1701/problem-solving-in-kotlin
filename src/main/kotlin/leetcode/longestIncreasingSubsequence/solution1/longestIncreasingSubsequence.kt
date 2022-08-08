package leetcode.longestIncreasingSubsequence.solution1

import kotlin.math.max

class Solution {
    fun lengthOfLIS(nums: IntArray): Int {
        val seqLen = MutableList(nums.size) { 1 }
        var maxLen = 1

        for (i in 1 until nums.size) {
            for (j in 0 until i) {
                if (nums[i] > nums[j] && seqLen[j] + 1 > seqLen[i]) {
                    seqLen[i] = seqLen[j] + 1
                }
            }

            maxLen = max(maxLen, seqLen[i])
        }

        return maxLen
    }
}