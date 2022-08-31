package leetcode.sortTheMatrixDiagonally.solution1

import java.util.*
import kotlin.math.min

class Solution {
    fun diagonalSort(mat: Array<IntArray>): Array<IntArray> {
        val m = mat.size
        val n = mat[0].size

        for (r in 0 until m) {
            sortDiagonal(r, 0, mat)
        }

        for (c in 1 until n) {
            sortDiagonal(0, c, mat)
        }

        return mat
    }

    fun sortDiagonal(r: Int, c: Int, mat: Array<IntArray>) {
        val m = mat.size
        val n = mat[0].size
        val heap = PriorityQueue<Int>()

        val diagonalLength = min(m - r, n - c)

        for (i in 0 until diagonalLength) {
            heap.add(mat[r + i][c + i])
        }

        for (i in 0 until diagonalLength) {
            mat[r + i][c + i] = heap.poll()
        }
    }
}