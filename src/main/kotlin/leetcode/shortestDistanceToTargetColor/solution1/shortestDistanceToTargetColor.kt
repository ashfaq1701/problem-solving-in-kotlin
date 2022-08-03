package leetcode.shortestDistanceToTargetColor.solution1

import java.util.Collections.binarySearch
import kotlin.math.min

class Solution {

    fun shortestDistanceColor(colors: IntArray, queries: Array<IntArray>): List<Int> {
        val idxMap = computeIdxMap(colors)

        return queries.map { (idx, color) ->
            if (color in idxMap) {
                findClosestNumber(idxMap[color]!!, idx)
            } else {
                -1
            }
        }
    }

    fun computeIdxMap(colors: IntArray): Map<Int, List<Int>> {
        val idxMap = mutableMapOf<Int, MutableList<Int>>()

        for (i in colors.indices) {
            if (colors[i] !in idxMap) {
                idxMap[colors[i]] = mutableListOf()
            }

            idxMap[colors[i]]!!.add(i)
        }

        return idxMap
    }

    fun findClosestNumber(positions: List<Int>, idx: Int): Int {
        var insertPosition = binarySearch(positions, idx)

        if (insertPosition < 0) {
            insertPosition = -(insertPosition + 1)
        }

        return if (insertPosition == 0) {
            positions[0] - idx
        } else if (insertPosition == positions.size) {
            idx - positions.last()
        } else {
            val leftDist = idx - positions[insertPosition - 1]
            val rightDist = positions[insertPosition] - idx
            min(leftDist, rightDist)
        }
    }
}

fun main() {
    println(Solution().shortestDistanceColor(intArrayOf(1,1,2,1,3,2,2,3,3), arrayOf(intArrayOf(1,3), intArrayOf(2,2), intArrayOf(6,1))))
}
