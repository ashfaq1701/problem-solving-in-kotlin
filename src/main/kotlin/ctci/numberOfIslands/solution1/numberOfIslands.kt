package ctci.numberOfIslands.solution1

class Solution {
    fun numIslands(grid: Array<CharArray>): Int {
        val visited = List(grid.size) { MutableList(grid[0].size) { false } }
        var islands = 0

        for (r in 0 until grid.size) {
            for (c in 0 until grid[0].size) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    islands += 1
                    dfs(grid, r, c, visited)
                }
            }
        }

        return islands
    }

    fun dfs(grid: Array<CharArray>, r: Int, c: Int, visited: List<MutableList<Boolean>>) {
        if (visited[r][c]) return

        visited[r][c] = true

        for ((adjR, adjC) in getNeighbors(r, c, grid.size, grid[0].size)) {
            if (grid[adjR][adjC] == '1') {
                dfs(grid, adjR, adjC, visited)
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