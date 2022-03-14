package leetcode.diagonalTraverse.solution1

class Solution {
    fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
        var goingDown = false

        var currentR = 0
        var currentC = 0

        val rows = mat.size
        val cols = mat[0].size

        val result = mutableListOf<Int>()

        while (currentR in 0 until rows && currentC in 0 until cols) {
            result.add(mat[currentR][currentC])

            if (goingDown) {
                if (currentC == 0 || currentR == rows - 1) {
                    if (currentR == rows - 1) {
                        currentC += 1
                    } else {
                        currentR += 1
                    }

                    goingDown = false
                    continue
                }

                currentR += 1
                currentC -= 1

            } else {
                if (currentC == cols - 1 || currentR == 0) {
                    if (currentC == cols - 1) {
                        currentR += 1
                    } else {
                        currentC += 1
                    }

                    goingDown = true
                    continue
                }

                currentR -= 1
                currentC += 1
            }
        }

        return result.toIntArray()
    }
}