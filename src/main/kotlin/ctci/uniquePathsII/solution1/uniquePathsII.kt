package ctci.uniquePathsII.solution1

class Solution {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val cache = MutableList(obstacleGrid.size) { MutableList(obstacleGrid[0].size) { -1 } }
        return waysToBottom(obstacleGrid, 0, 0, cache)
    }

    fun waysToBottom(obstacleGrid: Array<IntArray>, r: Int, c: Int, cache: MutableList<MutableList<Int>>): Int {
        val rows = obstacleGrid.size
        val cols = obstacleGrid[0].size

        if (obstacleGrid[r][c] == 1) {
            return 0
        }

        if (r == rows - 1 && c == cols - 1) {
            return 1
        }

        if (cache[r][c] != -1) {
            return cache[r][c]
        }

        var ways = 0

        if (r < rows - 1) {
            ways += waysToBottom(obstacleGrid, r + 1, c, cache)
        }

        if (c < cols - 1) {
            ways += waysToBottom(obstacleGrid, r, c + 1, cache)
        }

        cache[r][c] = ways

        return ways
    }
}