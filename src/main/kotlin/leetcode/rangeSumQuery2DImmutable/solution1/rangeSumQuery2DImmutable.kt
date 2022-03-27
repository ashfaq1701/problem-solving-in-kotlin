package leetcode.rangeSumQuery2DImmutable.solution1

class NumMatrix(matrix: Array<IntArray>) {

    private val infoMatrix: List<List<Int>> by lazy {
        val temp = MutableList(matrix.size) { MutableList(matrix[0].size) { 0 } }

        for (c in matrix[0].indices) {
            temp[0][c] = matrix[0][c]
        }

        for (r in 1 until matrix.size) {
            for (c in matrix[0].indices) {
                temp[r][c] = temp[r - 1][c] + matrix[r][c]
            }
        }

        for (r in matrix.indices) {
            for (c in 1 until matrix[0].size) {
                temp[r][c] += temp[r][c - 1]
            }
        }

        temp
    }

    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        var sum = infoMatrix[row2][col2]

        if (row1 - 1 >= 0) {
            sum -= infoMatrix[row1 - 1][col2]
        }

        if (col1 - 1 >= 0) {
            sum -= infoMatrix[row2][col1 - 1]
        }

        if (row1 - 1 >= 0 && col1 - 1 >= 0) {
            sum += infoMatrix[row1 - 1][col1 - 1]
        }

        return sum
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * var obj = NumMatrix(matrix)
 * var param_1 = obj.sumRegion(row1,col1,row2,col2)
 */