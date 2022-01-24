package ctci.rotateImage.solution1

class Solution {
    fun rotate(matrix: Array<IntArray>): Unit {
        transpose(matrix)
        reflect(matrix)
    }

    fun transpose(matrix: Array<IntArray>): Unit {
        for (r in 0 until matrix.size) {
            for (c in r until matrix[0].size) {
                matrix[r][c] = matrix[c][r].also {
                    matrix[c][r] = matrix[r][c]
                }
            }
        }
    }

    fun reflect(matrix: Array<IntArray>): Unit {
        for (r in 0 until matrix.size) {
            reverseRow(matrix[r])
        }
    }

    fun reverseRow(row: IntArray) {
        var left = 0
        var right = row.lastIndex

        while (left < right) {
            row[left] = row[right].also {
                row[right] = row[left]
            }

            left += 1
            right -= 1
        }
    }
}