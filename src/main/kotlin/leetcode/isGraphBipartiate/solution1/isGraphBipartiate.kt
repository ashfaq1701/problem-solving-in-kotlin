package leetcode.isGraphBipartiate.solution1

import java.util.*

enum class Color { UNINITIALIZED, BLACK, WHITE }

class Solution {
    fun isBipartite(graph: Array<IntArray>): Boolean {
        val colors = MutableList(graph.size) { Color.UNINITIALIZED }

        for (start in 0 until graph.size) {
            if (colors[start] != Color.UNINITIALIZED) {
                continue
            }

            val stack = Stack<Int>()
            stack.push(start)
            colors[start] = Color.BLACK

            while (stack.isNotEmpty()) {
                val current = stack.pop()

                for (adj in graph[current]) {
                    if (colors[adj] == Color.UNINITIALIZED) {
                        stack.push(adj)
                        colors[adj] = getOppositeColor(colors[current])
                    } else if (colors[adj] == colors[current]) {
                        return false
                    }
                }
            }
        }

        return true
    }

    private fun getOppositeColor(color: Color) =
        if (color == Color.BLACK) Color.WHITE else Color.BLACK
}