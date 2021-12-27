package ae.medium.searchInSortedMatrix.solution1

// O(n + m) time | O(1) space
fun searchInSortedMatrix(matrix: List<List<Int>>, target: Int): Pair<Int, Int> {
    // Start from top-right
    var r = 0
    var c = matrix[0].lastIndex

    while (r < matrix.size && c >= 0) {
        if (matrix[r][c] < target) {
            r += 1
        } else if (matrix[r][c] > target) {
            c -= 1
        } else {
            return r to c
        }
    }

    return -1 to -1
}
