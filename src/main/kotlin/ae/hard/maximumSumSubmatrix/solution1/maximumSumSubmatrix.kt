package ae.hard.maximumSumSubmatrix.solution1

import kotlin.math.max

fun maximumSumSubmatrix(matrix: List<List<Int>>, size: Int): Int {
    // Each element considered to be the bottom right corner of the rectangle
    // Formula sum = sums[r][c] - sums[r - size][c] - sums[r][c - size] + sums[r - size][c - size]
    val sumMatrix = createSumMatrix(matrix)
    var maxSum = Integer.MIN_VALUE

    // i = (size - 1) and j = (size - 1) constitutes the first
    // size * size rectangle whose bottom right corner is at (i, j)
    for (r in size - 1 until matrix.size) {
        for (c in size - 1 until matrix[0].size) {
            var total = sumMatrix[r][c]

            val touchesTopBorder = r - size < 0
            if (!touchesTopBorder) {
                total -= sumMatrix[r - size][c]
            }

            val touchesLeftBorder = c - size < 0
            if (!touchesLeftBorder) {
                total -= sumMatrix[r][c - size]
            }

            val touchesAnyBorder = touchesTopBorder || touchesLeftBorder
            if (!touchesAnyBorder) {
                total += sumMatrix[r - size][c - size]
            }

            maxSum = max(maxSum, total)
        }
    }

    return maxSum
}

fun createSumMatrix(matrix: List<List<Int>>): List<List<Int>> {
    val sumMatrix = MutableList(matrix.size) {
        MutableList(matrix[0].size) { 0 }
    }

    sumMatrix[0][0] = matrix[0][0]

    // Populate first row
    for (c in 1 until matrix[0].size) {
        sumMatrix[0][c] = matrix[0][c] + sumMatrix[0][c - 1]
    }

    // Populate first column
    for (r in 1 until matrix.size) {
        sumMatrix[r][0] = matrix[r][0] + sumMatrix[r - 1][0]
    }

    // sums[r][c] = matrix[r][c] + sumMatrix[r - 1][c] + sumMatrix[r][c - 1] - sumMatrix[r - 1][c - 1]
    for (r in 1 until matrix.size) {
        for (c in 1 until matrix[0].size) {
            sumMatrix[r][c] = matrix[r][c] + sumMatrix[r - 1][c] + sumMatrix[r][c - 1] - sumMatrix[r - 1][c - 1]
        }
    }

    return sumMatrix
}
