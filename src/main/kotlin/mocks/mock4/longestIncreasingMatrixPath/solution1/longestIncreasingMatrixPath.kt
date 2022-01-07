package mocks.mock4.longestIncreasingMatrixPath.solution1

import kotlin.math.max

fun longestIncreasingMatrixPath(matrix: List<List<Int>>): Int {
    val cache = MutableList(matrix.size) {
        MutableList(matrix[0].size) { 0 }
    }

    var longestPathLen = 0
    for (r in matrix.indices) {
        for (c in matrix[0].indices) {
            longestPathLen = max(
                longestPathLen,
                getLongestIncreasingPathAt(matrix, r, c, cache)
            )
        }
    }

    return longestPathLen
}

fun getLongestIncreasingPathAt(matrix: List<List<Int>>, r: Int, c: Int, cache: MutableList<MutableList<Int>>): Int {
    if (cache[r][c] > 0) return cache[r][c]

    var maxLen = 0

    for ((adjR, adjC) in getNeighbors(matrix, r, c)) {
        if (matrix[adjR][adjC] > matrix[r][c]) {
            maxLen = max(
                maxLen,
                getLongestIncreasingPathAt(matrix, adjR, adjC, cache)
            )
        }
    }

    cache[r][c] = maxLen + 1
    return cache[r][c]
}

fun getNeighbors(matrix: List<List<Int>>, row: Int, col: Int): List<Pair<Int, Int>> {
    return listOf(
        row - 1 to col,
        row to col - 1,
        row + 1 to col,
        row to col + 1
    ).filter { (r, c) ->
        r >= 0 && r < matrix.size && c >= 0 && c < matrix[0].size
    }
}
