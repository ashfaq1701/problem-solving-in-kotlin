package leetcode.toeplitzMatrix.solution1

class Solution {
    fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
        var row = 0
        var col = 0

        for (r in 0 until matrix.size) {
            if (!checkDiagonal(r, 0, matrix)) {
                return false
            }
        }

        for (c in 1 until matrix[0].size) {
            if (!checkDiagonal(0, c, matrix)) {
                return false
            }
        }

        return true
    }

    fun checkDiagonal(r: Int, c: Int, matrix: Array<IntArray>): Boolean {
        val num = matrix[r][c]

        var currentR = r
        var currentC = c

        while (currentR < matrix.size && currentC < matrix[0].size) {
            if (matrix[currentR][currentC] != num) return false

            currentR += 1
            currentC += 1
        }

        return true
    }
}