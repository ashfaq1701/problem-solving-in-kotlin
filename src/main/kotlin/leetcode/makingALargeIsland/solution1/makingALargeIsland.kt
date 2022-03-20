package leetcode.makingALargeIsland.solution1

import kotlin.math.max

class Solution {
    fun largestIsland(grid: Array<IntArray>): Int {
        var idx = 2

        var largestArea = 0
        val areaMap = mutableMapOf<Int, Int>()

        for (r in 0 until grid.size) {
            for (c in 0 until grid[0].size) {
                if (grid[r][c] == 1) {
                    val area = dfs(r, c, grid, idx)
                    largestArea = max(area, largestArea)
                    areaMap[idx] = area
                    idx += 1
                }
            }
        }

        for (r in 0 until grid.size) {
            for (c in 0 until grid[0].size) {
                if (grid[r][c] == 0) {
                    val neighborIdSet = getNeighbors(r, c, grid.size, grid[0].size).map { (nR, nC) ->
                        grid[nR][nC]
                    }.toSet()

                    var mergedArea = 1

                    for (neighborId in neighborIdSet) {
                        if (neighborId > 1) {
                            mergedArea += areaMap[neighborId]!!
                        }
                    }

                    largestArea = max(mergedArea, largestArea)
                }
            }
        }

        return largestArea
    }

    private fun dfs(r: Int, c: Int, grid: Array<IntArray>, idx: Int): Int {
        var area = 1

        grid[r][c] = idx

        for (neighbor in getNeighbors(r, c, grid.size, grid[0].size)) {
            val (neighborR, neighborC) = neighbor

            if (grid[neighborR][neighborC] == 1) {
                area += dfs(neighborR, neighborC, grid, idx)
            }
        }

        return area
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
}