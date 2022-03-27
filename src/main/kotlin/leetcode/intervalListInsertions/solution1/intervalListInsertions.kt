package leetcode.intervalListInsertions.solution1

import kotlin.math.min
import kotlin.math.max

class Solution {
    fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
        var i = 0
        var j = 0

        val mergedIntervals = mutableListOf<IntArray>()

        while (i < firstList.size && j < secondList.size) {
            val (firstStart, firstEnd) = firstList[i]
            val (secondStart, secondEnd) = secondList[j]

            val maxStart = max(firstStart, secondStart)
            val minEnd = min(firstEnd, secondEnd)

            if (maxStart <= minEnd) {
                mergedIntervals.add(intArrayOf(maxStart, minEnd))
            }

            if (firstEnd < secondEnd) {
                i += 1
            } else {
                j += 1
            }
        }

        return mergedIntervals.toTypedArray()
    }
}