package ctci.numberOfIslands.solution2

import java.util.*

class Solution {
    fun numIslands(grid: Array<CharArray>): Int {
        var visited = List(grid.size) { MutableList(grid[0].size) { false } }
        var islands = 0

        for (r in 0 until grid.size) {
            for (c in 0 until grid[0].size) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    islands += 1
                    bfs(grid, r, c, visited)
                }
            }
        }

        return islands
    }

    fun bfs(grid: Array<CharArray>, r: Int, c: Int, visited: List<MutableList<Boolean>>) {
        val queue = LinkedList<Pair<Int, Int>>()
        queue.add(r to c)

        while (queue.isNotEmpty()) {
            val (currR, currC) = queue.poll()

            if (visited[currR][currC]) continue

            visited[currR][currC] = true

            for (neighbor in getNeighbors(currR, currC, grid.size, grid[0].size)) {
                val (neighborR, neighborC) = neighbor

                if (grid[neighborR][neighborC] == '1') {
                    queue.add(neighbor)
                }
            }
        }
    }

    fun getNeighbors(r: Int, c: Int, rows: Int, cols: Int): List<Pair<Int, Int>> {
        return listOf(
            r - 1 to c,
            r to c - 1,
            r + 1 to c,
            r to c + 1
        ).filter { (row, col) ->
            row in 0 until rows && col in 0 until cols
        }
    }
}