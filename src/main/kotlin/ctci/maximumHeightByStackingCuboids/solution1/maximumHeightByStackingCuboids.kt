package ctci.maximumHeightByStackingCuboids.solution1

import kotlin.math.max

class Solution {
    fun maxHeight(cuboids: Array<IntArray>): Int {
        val sortedCuboids = cuboids.map { cuboid -> cuboid.sorted() }.toMutableList()
        sortedCuboids.sortWith (Comparator<List<Int>> { cuboid1, cuboid2 ->
            // it is obvious that if one cube can stack on the other cube that means its height, width, length are all larger than the other one.
            cuboid1.sum().compareTo(cuboid2.sum())
        })

        val heights = sortedCuboids.map { it[2] }.toMutableList()

        var maxHeight = heights[0]

        for (currentIdx in 1 until sortedCuboids.size) {
            val currentHeight = sortedCuboids[currentIdx][2]

            for (prevIdx in 0 until currentIdx) {
                if (canStack(sortedCuboids[currentIdx], sortedCuboids[prevIdx]) && currentHeight + heights[prevIdx] > heights[currentIdx]) {
                    heights[currentIdx] = heights[prevIdx] + currentHeight
                }

                maxHeight = max(maxHeight, heights[currentIdx])
            }
        }

        return maxHeight
    }

    fun canStack(bottomCuboid: List<Int>, topCuboid: List<Int>): Boolean {
        return topCuboid[0] <= bottomCuboid[0] && topCuboid[1] <= bottomCuboid[1] && topCuboid[2] <= bottomCuboid[2]
    }
}