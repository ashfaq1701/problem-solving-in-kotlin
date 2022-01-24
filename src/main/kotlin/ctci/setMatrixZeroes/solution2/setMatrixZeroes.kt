package ctci.setMatrixZeroes.solution2

class Solution {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        var isFirstColZero = false

        for (r in 0 .. matrix.lastIndex) {

            // If any element in the first column is 0, then set the variable isFirstColZero.
            if (matrix[r][0] == 0) {
                isFirstColZero = true
            }

            // We checked first column before, so here start from the second column.
            // This is to prevent 0,0 index to reset to 0 for both first row and col
            for (c in 1 .. matrix[0].lastIndex) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0
                    matrix[0][c] = 0
                }
            }
        }

        // First row and column will be handled separately
        for (r in 1 .. matrix.lastIndex) {
            for (c in 1 .. matrix[0].lastIndex) {
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0
                }
            }
        }

        // Handle first row. matrix[0][0] was not be manipulated by column
        // So 0 here means that any value in the 0th row was zero.
        if (matrix[0][0] == 0) {
            for (c in 0 .. matrix[0].lastIndex) {
                matrix[0][c] = 0
            }
        }

        // Handle first column.
        if (isFirstColZero) {
            for (r in 0 .. matrix.lastIndex) {
                matrix[r][0] = 0
            }
        }
    }
}