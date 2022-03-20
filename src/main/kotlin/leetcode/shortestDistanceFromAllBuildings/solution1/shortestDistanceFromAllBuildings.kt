package leetcode.shortestDistanceFromAllBuildings.solution1

import java.util.LinkedList
import kotlin.math.min

data class EmptyLandData(var accessibleFromBuildings: Int = 0, var totalDistanceFromBuildings: Int = 0)

class Solution {
    fun shortestDistance(grid: Array<IntArray>): Int {
        var buildingCount = 0
        val distanceData = mutableMapOf<String, EmptyLandData>()

        for (r in 0 until grid.size) {
            for (c in 0 until grid[0].size) {
                if (grid[r][c] == 1) {
                    buildingCount += 1
                    getDistancesToEmptyLands(r, c, grid, distanceData)
                }
            }
        }

        var minDistance = Int.MAX_VALUE
        for ((_, landData) in distanceData) {
            if (landData.accessibleFromBuildings == buildingCount) {
                minDistance = min(landData.totalDistanceFromBuildings, minDistance)
            }
        }

        return if (minDistance == Int.MAX_VALUE) -1 else minDistance
    }

    private fun getDistancesToEmptyLands(startRow: Int, startCol: Int, grid: Array<IntArray>, distanceData: MutableMap<String, EmptyLandData>) {
        val queue = LinkedList<Pair<Pair<Int, Int>, Int>>()
        val visited = mutableSetOf<String>()

        queue.add((startRow to startCol) to 0)

        while (queue.isNotEmpty()) {
            val (current, dist) = queue.poll()
            val (currentR, currentC) = current
            val currentKey = coordToString(current)

            if (currentKey in visited) continue
            visited.add(currentKey)

            if (grid[currentR][currentC] == 2) {
                continue
            }

            if (grid[currentR][currentC] == 1 && dist > 0) {
                continue
            }

            if (grid[currentR][currentC] == 0) {
                if (currentKey !in distanceData) {
                    distanceData[currentKey] = EmptyLandData()
                }

                distanceData[currentKey]!!.apply {
                    accessibleFromBuildings += 1
                    totalDistanceFromBuildings += dist
                }
            }

            for (adj in getNeighbors(currentR, currentC, grid.size, grid[0].size)) {
                val adjKey = coordToString(adj)
                if (adjKey !in visited) {
                    queue.add(adj to dist + 1)
                }
            }
        }
    }

    private fun getNeighbors(r: Int, c: Int, rows: Int, cols: Int): List<Pair<Int, Int>> {
        return listOf(
            r - 1 to c,
            r to c - 1,
            r + 1 to c,
            r to c + 1
        ).filter { (currentR, currentC) ->
            currentR in 0 until rows && currentC in 0 until cols
        }
    }

    private fun coordToString(p: Pair<Int, Int>) = "${p.first}|${p.second}"
}