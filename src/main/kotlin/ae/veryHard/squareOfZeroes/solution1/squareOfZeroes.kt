package ae.veryHard.squareOfZeroes.solution1

fun squareOfZeroes(matrix: List<List<Int>>): Boolean {

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
                if (isSquareOfZeroes(matrix, r, c, r + sideLen - 1, c + sideLen - 1)) {
                    return true
                }

                // Try to increase sideLen from 2 to 3, 4, 5 etc.
                sideLen++
            }
        }
    }

    return false
}

fun isSquareOfZeroes(matrix: List<List<Int>>, r1: Int, c1: Int, r2: Int, c2: Int): Boolean {
    // For all rows check if start and end columns are not 0
    // If not then square is not square of zeroes
    for (r in r1 .. r2) {
        if (matrix[r][c1] != 0 || matrix[r][c2] != 0) {
            return false
        }
    }

    // For all cols check if start and end rows are not 0
    // If not then square is not square of zeroes
    for (c in c1 .. c2) {
        if (matrix[r1][c] != 0 || matrix[r2][c] != 0) {
            return false
        }
    }

    return true
}