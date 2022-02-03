package ctci.searchInA2DMatrixII.solution1

class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var currentRow = 0
        var currentCol = matrix[0].lastIndex

        while (currentRow < matrix.size && currentCol >= 0) {
            if (matrix[currentRow][currentCol] == target) {
                return true
            } else if (matrix[currentRow][currentCol] < target) {
                currentRow += 1
            } else {
                currentCol -= 1
            }
        }

        return false
    }
}