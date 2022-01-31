package ctci.maximumHeightByStackingCuboids.solution1

import kotlin.math.max

class Solution {
    fun maxHeight(cuboids: Array<IntArray>): Int {
        val sortedCuboids = cuboids.map { cuboid -> cuboid.sorted() }.toMutableList()
        sortedCuboids.sortWith (Comparator<List<Int>> { cuboid1, cuboid2 ->
            if (cuboid1[0] != cuboid2[0]) {
                cuboid1[0] - cuboid2[0]
            } else if (cuboid1[1] != cuboid2[1]) {
                cuboid1[1] - cuboid2[1]
            } else {
                cuboid1[2] - cuboid2[2]
            }
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