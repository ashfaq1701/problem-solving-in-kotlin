package ctci.setMatrixZeroes.solution1

class Solution {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val zeroRows = mutableListOf<Int>()
        val zeroCols = mutableListOf<Int>()

        for (r in 0 .. matrix.lastIndex) {
            for (c in 0 .. matrix[0].lastIndex) {
                if (matrix[r][c] == 0) {
                    zeroRows.add(r)
                    zeroCols.add(c)
                }
            }
        }

        for (r in 0 .. matrix.lastIndex) {
            for (c in 0 .. matrix[0].lastIndex) {
                if (r in zeroRows || c in zeroCols) {
                    matrix[r][c] = 0
                }
            }
        }
    }
}