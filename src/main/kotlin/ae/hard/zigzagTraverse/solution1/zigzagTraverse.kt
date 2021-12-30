package ae.hard.zigzagTraverse.solution1

fun zigzagTraverse(array: List<List<Int>>): List<Int> {
    val lastRow = array.lastIndex
    val lastCol = array[0].lastIndex

    val result = mutableListOf<Int>()
    var goingDown = true

    var row = 0
    var col = 0

    while (!isOutOfBounds(row, col, lastRow, lastCol)) {
        result.add(array[row][col])

        if (goingDown) {

            if (col == 0 || row == lastRow) {
                goingDown = false

                if (row == lastRow) {
                    col += 1
                } else {
                    row += 1
                }
            } else {
                row += 1
                col -= 1
            }
        } else {

            if (row == 0 || col == lastCol) {
                goingDown = true

                if (col == lastCol) {
                    row += 1
                } else {
                    col += 1
                }
            } else {
                row -= 1
                col += 1
            }
        }
    }

    return result
}

fun isOutOfBounds(row: Int, col: Int, lastRow: Int, lastCol: Int): Boolean {
    return row < 0 || row > lastRow || col < 0 || col > lastCol
}