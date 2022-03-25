package ctci.maximalSquare.solution1

import kotlin.math.max

class Solution {
    var maxSideLen = 0

    fun maximalSquare(matrix: Array<CharArray>): Int {
        val memo = MutableList(matrix.size) { MutableList(matrix[0].size) { -1 } }
        maximalSideHelper(matrix, 0, 0, memo)
        return maxSideLen * maxSideLen
    }

    fun maximalSideHelper(matrix: Array<CharArray>, r: Int, c: Int, memo: MutableList<MutableList<Int>>): Int {
        if (r >= matrix.size || c >= matrix[0].size) return 0

        if (memo[r][c] != -1) return memo[r][c]

        val neighborMinSideLen = listOf(
            maximalSideHelper(matrix, r + 1, c, memo),
            maximalSideHelper(matrix, r, c + 1, memo),
            maximalSideHelper(matrix, r + 1, c + 1, memo)
        ).minOrNull()!!

        memo[r][c] = if (matrix[r][c] == '1') {
            1 + neighborMinSideLen
        } else 0

        maxSideLen = max(memo[r][c], maxSideLen)

        return memo[r][c]
    }
}