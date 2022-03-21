package leetcode.partitionLabels.solution1

import kotlin.math.max

class Solution {
    fun partitionLabels(s: String): List<Int> {
        val lastOccourances = mutableMapOf<Char, Int>()

        for (idx in s.indices) {
            lastOccourances[s[idx]] = idx
        }

        val result = mutableListOf<Int>()

        var currentPartitionEnd = 0
        var currentPartitionStart = 0
        for (i in s.indices) {
            currentPartitionEnd = max(currentPartitionEnd, lastOccourances[s[i]]!!)

            if (i == currentPartitionEnd) {
                result.add(currentPartitionEnd - currentPartitionStart + 1)
                currentPartitionStart = currentPartitionEnd + 1
            }
        }

        return result
    }
}