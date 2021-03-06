package ae.medium.minimumPassesOfMatrix.solution1

import java.util.LinkedList

fun minimumPassesOfMatrix(matrix: MutableList<MutableList<Int>>): Int {
    val passes = convertNegatives(matrix)
    return if (isAllPositive(matrix)) passes - 1 else -1
}

fun convertNegatives(matrix: MutableList<MutableList<Int>>): Int {
    var nextPassQueue = getPositiveIndices(matrix)

    var passes = 0

    while (nextPassQueue.isNotEmpty()) {
        val currentPassQueue = nextPassQueue
        nextPassQueue = LinkedList<List<Int>>()

        while (currentPassQueue.isNotEmpty()) {
            val (currentRow, currentCol) = currentPassQueue.poll()
            val neighbors = getNeighbors(currentRow, currentCol, matrix)

            for (neighbor in neighbors) {
                val (neighborR, neighborC) = neighbor

                if (matrix[neighborR][neighborC] < 0) {
                    matrix[neighborR][neighborC] *= -1
                    nextPassQueue.add(neighbor)
                }
            }
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