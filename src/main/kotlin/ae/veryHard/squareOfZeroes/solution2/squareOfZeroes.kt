package ae.veryHard.squareOfZeroes.solution2

// O(n^3) Time | O(n ^ 2) Space
fun squareOfZeroes(matrix: List<List<Int>>): Boolean {
    val infoMatrix = computeInfoMatrix(matrix)

    val len = matrix.size

    // For each element
    for (r in 0 until len) {
        for (c in 0 until len) {

            // Consider this cell as the top left corner of a square
            // Start with square of side length 2
            var sideLen = 2

            // While side of length sideLen from this cell are in bound
            while (r + sideLen - 1 < len && c + sideLen - 1 < len) {

                // Call function to check if this square is a square of zero
                if (isSquareOfZeroes(infoMatrix, r, c, r + sideLen - 1, c + sideLen - 1)) {
                    return true
                }

                // Try to increase sideLen from 2 to 3, 4, 5 etc.
                sideLen++
            }
        }
    }

    return false
}

data class CellInfo(var rightZeroesCount: Int, var bottomZeroesCount: Int)

fun computeInfoMatrix(matrix: List<List<Int>>): List<List<CellInfo>> {
    val len = matrix.size

    // Info matrix of n * n
    val infoMatrix = MutableList(len) {
        MutableList(len) { CellInfo(0, 0) }
    }

    for (r in 0 until len) {
        for (c in 0 until len) {
            // Mark all of the cells which has '0' value with {1, 1}
            // So the assumption is that in this position
            // there are 1 consecutive 0 to the right and 1 consecutive 0 to bottom
            // So only this '0' cell
            if (matrix[r][c] == 0) {
                infoMatrix[r][c] = CellInfo(1, 1)
            }
        }
    }

    // Start from last row last column and decrement towards top-left
    // For all rows starting from last to the 1st row
    for (r in len - 1 downTo 0) {
        // For all columns starting from last to the 1st column
        for (c in len - 1 downTo 0) {

            // If value is 1, then there are 0 consecutive 0 value in bottom and right
            // So continue
            if (matrix[r][c] == 1) {
                continue
            }

            // After last row there is no row at it's bottom
            if (r < len - 1) {
                infoMatrix[r][c].bottomZeroesCount += infoMatrix[r + 1][c].bottomZeroesCount
            }

            // After last column there is no column at it's right
            if (c < len - 1) {
                infoMatrix[r][c].rightZeroesCount += infoMatrix[r][c + 1].rightZeroesCount
            }
        }
    }

    // Return the accumulated info matrix
    return infoMatrix
}

fun isSquareOfZeroes(infoMatrix: List<List<CellInfo>>, r1: Int, c1: Int, r2: Int, c2: Int): Boolean {
    // Calculate width and height
    val width = c2 - c1 + 1
    val height = r2 - r1 + 1

    // If the top left point has more than or equal to amount
    // of consecutive zeroes in the bottom direction
    val isLeftColZeroes = infoMatrix[r1][c1].bottomZeroesCount >= height

    // If the top left point has more than or equal to amount
    // of consecutive zeroes in the right direction
    val isTopRowZeroes = infoMatrix[r1][c1].rightZeroesCount >= width

    // If the top right point has more than or equal to amount
    // of consecutive zeroes in the bottom direction
    val isRightColZeroes = infoMatrix[r1][c2].bottomZeroesCount >= height

    // If the bottom left point has more than or equal to amount
    // of consecutive zeroes in the right direction
    val isBottomRowZeroes = infoMatrix[r2][c1].rightZeroesCount >= width

    // If all of these are true then we have a square of zeroes.
    return isLeftColZeroes && isTopRowZeroes && isRightColZeroes && isBottomRowZeroes
}