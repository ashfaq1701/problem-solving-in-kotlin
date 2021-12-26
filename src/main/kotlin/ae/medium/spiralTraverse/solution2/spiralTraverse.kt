package ae.medium.spiralTraverse.solution2

fun spiralTraverse(array: List<List<Int>>): List<Int> {
    val result = mutableListOf<Int>()
    spiralFill(array, 0, array.lastIndex, 0, array[0].lastIndex, result)
    return result
}

fun spiralFill(array: List<List<Int>>, startRow: Int, endRow: Int, startCol: Int, endCol: Int, result: MutableList<Int>) {
    if (startRow > endRow || startCol > endCol) {
        return
    }

    for (c in startCol .. endCol) {
        result.add(array[startRow][c])
    }

    for (r in startRow + 1 .. endRow) {
        result.add(array[r][endCol])
    }

    for (c in endCol - 1 downTo startCol) {
        if (startRow == endRow) break
        result.add(array[endRow][c])
    }

    for (r in endRow - 1 downTo startRow + 1) {
        if (startCol == endCol) break
        result.add(array[r][startCol])
    }

    spiralFill(array, startRow + 1, endRow - 1, startCol + 1, endCol - 1, result)
}
