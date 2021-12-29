package ae.hard.solveSudoku.solution1

fun solveSudoku(board: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
    // Start solving sudoku board recursively and change it in-place.
    solvePartialSudoku(0, 0, board)
    return board
}

// Solve sudoku board per-position recursively.
fun solvePartialSudoku(row: Int, col: Int, board: MutableList<MutableList<Int>>): Boolean {
    var currentRow = row
    var currentCol = col

    // Column value get's incremented to make next recursive call.
    // If the column value gets passed the maximum column value then wrap column to 0.
    // Then increment the row to the next row.
    if (currentCol == board[0].size) {
        currentCol = 0
        currentRow += 1
    }

    // If the current row gets passed the maximum row value then the sudoku board is fully solved.
    // Return true in that case.
    if (currentRow == board.size) {
        return true
    }

    // If the board's current cell value is 0 (e.g the cell is unoccupied)
    // Then try to solve the cell.
    if (board[currentRow][currentCol] == 0) {
        return tryDigitsAtPosition(currentRow, currentCol, board)
    }

    // Otherwise current cell has value (it is not unoccupied).
    // Solve recursively for the next column.
    return solvePartialSudoku(currentRow, currentCol + 1, board)
}

fun tryDigitsAtPosition(row: Int, col: Int, board: MutableList<MutableList<Int>>): Boolean {
    // Try placing each digit between 1-9.
    for (digit in 1 .. 9) {
        // If this digit is valid in the position marked by row and col
        if (isValidAtPosition(digit, row, col, board)) {
            // Place the digit in the cell pointed by row and col.
            board[row][col] = digit

            // If we can solve the next column recursively in a valid way then this position is valid.
            // Return true in that case.
            if (solvePartialSudoku(row, col + 1, board)) {
                return true
            }
        }
    }

    // We reach here if no digit is valid in the position of row, col
    // OR we could not solve the next column (col + 1) recursively.

    // We mark the position marked with row, col as unoccupied and we return false.
    board[row][col] = 0
    return false
}

// This function checks if a value is valid in a row, col position
fun isValidAtPosition(digit: Int, row: Int, col: Int, board: MutableList<MutableList<Int>>): Boolean {
    // If the value is not present in currentRow then the value is valid in current row.
    val isValidInRow = !board[row].contains(digit)

    // If the value is not present in currentCol, then the value is valid in current col.
    val isValidInCol = board.fold(true) { isValid, currentRow ->
        isValid && currentRow[col] != digit
    }

    // If row or col is invalid, then just return false.
    if (!isValidInRow || !isValidInCol) {
        return false
    }

    // Get the 3x3 grid number of the passed row and column.
    // Example if passed row, col is 5, 7,
    // rowGridNumber = 1
    // colGridNumber = 2
    val rowGridNumber = row / 3
    val colGridNumber = col / 3

    // We only navigate in the current 3x3 grid.
    for (r in 0 until 3) {
        // We get the row index within the grid, gridNumber * 3 + r.
        val rowGridIdx = rowGridNumber * 3 + r

        for (c in 0 until 3) {
            // We get the col index within the grid, gridNumber * 3 + c.
            val colGridIdx = colGridNumber * 3 + c

            // If in the current position of the 3x3 grid, we find the value, then it is invalid.
            if (board[rowGridIdx][colGridIdx] == digit) {
                return false
            }
        }
    }

    // We passed all above sanity check, so the value is valid.
    return true
}
