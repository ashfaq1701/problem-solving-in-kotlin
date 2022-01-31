package ctci.floodFill.solution2

import java.util.*

class Solution {
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
        val queue = LinkedList<Pair<Int, Int>>()
        val oldColor = image[sr][sc]
        queue.add(sr to sc)

        while (queue.isNotEmpty()) {
            val (currentRow, currentCol) = queue.pop()

            if (image[currentRow][currentCol] == newColor || image[currentRow][currentCol] != oldColor) {
                continue
            }

            image[currentRow][currentCol] = newColor

            for (adj in getNeighbors(currentRow, currentCol, image.size, image[0].size)) {
                val (adjR, adjC) = adj

                if (image[adjR][adjC] == oldColor) {
                    queue.add(adj)
                }
            }
        }

        return image
    }

    fun getNeighbors(r: Int, c: Int, rows: Int, cols: Int): List<Pair<Int, Int>> {
        return listOf(
            r - 1 to c,
            r to c - 1,
            r + 1 to c,
            r to c + 1
        ).filter { (adjR, adjC) ->
            adjR in 0 until rows && adjC in 0 until cols
        }
    }
}