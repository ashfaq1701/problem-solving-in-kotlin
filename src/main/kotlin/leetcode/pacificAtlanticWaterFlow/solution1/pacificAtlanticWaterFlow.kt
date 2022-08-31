package leetcode.pacificAtlanticWaterFlow.solution1

import java.util.LinkedList

class Solution {

    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        var pacificQueue = LinkedList<List<Int>>()
        var atlanticQueue = LinkedList<List<Int>>()

        for (r in heights.indices) {
            pacificQueue.add(listOf(r, 0))
            atlanticQueue.add(listOf(r, heights[0].lastIndex))
        }

        for (c in heights[0].indices) {
            pacificQueue.add(listOf(0, c))
            atlanticQueue.add(listOf(heights.lastIndex, c))
        }

        val reachableByPacific = bfs(heights, pacificQueue)
        val reachableByAtlantic = bfs(heights, atlanticQueue)

        val resultGrids = mutableListOf<List<Int>>()

        for (r in heights.indices) {
            for (c in heights[0].indices) {
                if (reachableByPacific[r][c] && reachableByAtlantic[r][c]) {
                    resultGrids.add(listOf(r, c))
                }
            }
        }

        return resultGrids
    }

    private fun bfs(heights: Array<IntArray>, queue: LinkedList<List<Int>>): List<List<Boolean>> {
        val numRows = heights.size
        val numCols = heights[0].size
        val directions = listOf(listOf(-1, 0), listOf(0, 1), listOf(1, 0), listOf(0, -1))

        val visited = MutableList(numRows) { MutableList(numCols) { false } }

        while (queue.isNotEmpty()) {
            val (currentR, currentC) = queue.poll()

            if (visited[currentR][currentC]) continue

            visited[currentR][currentC] = true

            val currentHeight = heights[currentR][currentC]

            for ((dR, dC) in directions) {
                val adjR = currentR + dR
                val adjC = currentC + dC

                if (adjR in heights.indices
                    && adjC in heights[0].indices
                    && heights[adjR][adjC] >= currentHeight) {
                    queue.add(listOf(adjR, adjC))
                }
            }
        }

        return visited
    }
}