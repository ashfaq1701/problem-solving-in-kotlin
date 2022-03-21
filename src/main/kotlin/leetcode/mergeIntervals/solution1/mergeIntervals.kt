package leetcode.mergeIntervals.solution1

import kotlin.math.max

class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortWith(Comparator<IntArray> { i1, i2 ->
            i1[0].compareTo(i2[0])
        })

        val merged = mutableListOf(intervals[0])

        for (i in 1 .. intervals.lastIndex) {
            val currentInterval = intervals[i]
            val (start, end) = currentInterval
            if (start > merged.last()[1]) {
                merged.add(currentInterval)
            } else {
                merged.last()[1] = max(end, merged.last()[1])
            }
        }

        return merged.toTypedArray()
    }
}