package leetcode.theMazeII.solution1

import java.util.*

class Solution {
    fun shortestDistance(maze: Array<IntArray>, start: IntArray, destination: IntArray): Int {
        val distances = MutableList(maze.size) { MutableList(maze[0].size) { Int.MAX_VALUE } }
        distances[start[0]][start[1]] = 0
        dijkstra(maze, start, distances)
        return if (distances[destination[0]][destination[1]] == Int.MAX_VALUE) -1 else distances[destination[0]][destination[1]]
    }

    private fun dijkstra(maze: Array<IntArray>, start: IntArray, distances: MutableList<MutableList<Int>>) {
        val dirs = mutableListOf(0 to 1, 0 to -1, -1 to 0, 1 to 0)
        val queue = PriorityQueue<Pair<IntArray, Int>> { p1, p2 -> p1.second - p2.second }
        queue.add(start to 0)

        while (queue.isNotEmpty()) {
            val (current, currentDist) = queue.poll()
            if (distances[current[0]][current[1]] < currentDist) continue

            for (dir in dirs) {
                var x = current[0] + dir.first
                var y = current[1] + dir.second
                var count = 0

                while (x in maze.indices && y in maze[0].indices && maze[x][y] == 0) {
                    x += dir.first
                    y += dir.second
                    count += 1
                }

                x -= dir.first
                y -= dir.second

                if (distances[current[0]][current[1]] + count < distances[x][y]) {
                    distances[x][y] = distances[current[0]][current[1]] + count
                    queue.add(intArrayOf(x, y) to distances[x][y])
                }
            }
        }
    }
}