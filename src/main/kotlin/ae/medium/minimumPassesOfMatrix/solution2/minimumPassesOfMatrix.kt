package ae.medium.minimumPassesOfMatrix.solution2

import java.util.LinkedList

fun minimumPassesOfMatrix(matrix: MutableList<MutableList<Int>>): Int {
    val passes = convertNegatives(matrix)
    return if (isAllPositive(matrix)) passes - 1 else -1
}

fun convertNegatives(matrix: MutableList<MutableList<Int>>): Int {
    var queue = getPositiveIndices(matrix)

    var passes = 0

    while (queue.isNotEmpty()) {
        var currentSize = queue.size

        while (currentSize > 0) {
            val (currentRow, currentCol) = queue.poll()
            val neighbors = getNeighbors(currentRow, currentCol, matrix)

            for (neighbor in neighbors) {
                val (neighborR, neighborC) = neighbor

                if (matrix[neighborR][neighborC] < 0) {
                    matrix[neighborR][neighborC] *= -1
                    queue.add(neighbor)
                }
            }
            currentSize -= 1
        }

        passes += 1
    }

    return passes
}

fun getPositiveIndices(matrix: MutableList<MutableList<Int>>): LinkedList<List<Int>> {
    val queue = LinkedList<List<Int>>()

    for (r in 0 until matrix.size) {
        for (c in 0 until matrix[0].size) {
            if (matrix[r][c] > 0) {
                queue.add(listOf(r, c))
            }
        }
    }

    return queue
}

fun getNeighbors(currentRow: Int, currentCol: Int, matrix: MutableList<MutableList<Int>>): List<List<Int>> {
    val neighbors = mutableListOf(listOf(currentRow - 1, currentCol), listOf(currentRow, currentCol - 1), listOf(currentRow + 1, currentCol), listOf(currentRow, currentCol + 1))
    return neighbors.filter { (r, c) ->
        r >= 0 && r < matrix.size && c >= 0 && c < matrix[0].size
    }
}

fun isAllPositive(matrix: MutableList<MutableList<Int>>): Boolean {
    for (r in 0 until matrix.size) {
        for (c in 0 until matrix[0].size) {
            if (matrix[r][c] < 0) {
                return false
            }
        }
    }

    return true
}