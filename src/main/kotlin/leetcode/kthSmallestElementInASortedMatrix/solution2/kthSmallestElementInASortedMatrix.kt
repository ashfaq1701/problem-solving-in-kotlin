package leetcode.kthSmallestElementInASortedMatrix.solution2

import kotlin.math.min
import kotlin.math.max

class Solution {
    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val n = matrix.size
        var start = matrix[0][0]
        var end = matrix[n - 1][n - 1]

        while (start < end) {
            val mid = start + (end - start) / 2

            val smallLargePair = mutableListOf(matrix[0][0], matrix[n - 1][n - 1])
            val count = countLessAndEqual(matrix, mid, smallLargePair)

            if (count == k) {
                return smallLargePair[0]
            }

            if (count < k) {
                start = smallLargePair[1]
            } else {
                end = smallLargePair[0]
            }
        }

        return start
    }

    private fun countLessAndEqual(matrix: Array<IntArray>, mid: Int, smallLargePair: MutableList<Int>): Int {
        var count = 0

        val n = matrix.size
        var r = n - 1
        var c = 0

        while (r >= 0 && c < n) {
            if (matrix[r][c] > mid) {
                smallLargePair[1] = min(matrix[r][c], smallLargePair[1])
                r -= 1
            } else {
                smallLargePair[0] = max(matrix[r][c], smallLargePair[0])
                c += 1
                count += r + 1
            }
        }

        return count
    }
}