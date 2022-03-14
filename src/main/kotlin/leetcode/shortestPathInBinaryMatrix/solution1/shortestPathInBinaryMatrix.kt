package leetcode.shortestPathInBinaryMatrix.solution1

import java.util.LinkedList

class Solution {
    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
        val queue = LinkedList<Pair<Pair<Int, Int>, Int>>()

        if (grid[0][0] != 0) return -1

        queue.add((0 to 0) to 1)

        while (queue.isNotEmpty()) {
            val (current, len) = queue.poll()
            val (currentR, currentC) = current

            if (grid[currentR][currentC] != 0) continue

            grid[currentR][currentC] = 1

            if (currentR == grid.lastIndex && currentC == grid[0].lastIndex) {
                return len
            }

            for (adj in getAdjacent(currentR, currentC, grid)) {
                val (adjR, adjC) = adj
                if (grid[adjR][adjC] == 0) {
                    queue.add(adj to len + 1)
                }
            }
        }

        return -1
    }

    fun getAdjacent(r: Int, c: Int, grid: Array<IntArray>): List<Pair<Int, Int>> {
        return listOf(
            r - 1 to c,
            r to c - 1,
            r + 1 to c,
            r to c + 1,
            r - 1 to c - 1,
            r - 1 to c + 1,
            r + 1 to c - 1,
            r + 1 to c + 1
        ).filter { (row, col) ->
            row in 0 until grid.size && col in 0 until grid[0].size
        }
    }
}