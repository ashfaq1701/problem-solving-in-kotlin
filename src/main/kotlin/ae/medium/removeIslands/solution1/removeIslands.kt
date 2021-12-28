package ae.medium.removeIslands.solution1

fun removeIslands(matrix: List<MutableList<Int>>): List<MutableList<Int>> {
    val rows = matrix.size
    val cols = matrix[0].size

    val onesConnectedToBorder = MutableList(rows) { MutableList(cols) { false } }

    for (r in 0 until rows) {
        for (c in 0 until cols) {
            val rowIsBorder = r == 0 || r == rows - 1
            val colIsBorder = c == 0 || c == cols - 1

            if (!rowIsBorder && !colIsBorder) continue

            if (matrix[r][c] != 1) continue

            findOnesConnectedToBorder(r, c, matrix, onesConnectedToBorder)
        }
    }

    for (r in 1 until matrix.lastIndex) {
        for (c in 1 until matrix[0].lastIndex) {
            if (onesConnectedToBorder[r][c]) {
                continue
            }

            matrix[r][c] = 0
        }
    }

    return matrix
}

fun findOnesConnectedToBorder(r: Int, c: Int, matrix: List<MutableList<Int>>, onesConnectedToBorder: MutableList<MutableList<Boolean>>) {
    val stack = mutableListOf<Pair<Int, Int>>()
    stack.add(r to c)

    while (stack.isNotEmpty()) {
        val (currentRow, currentCol) = stack.last()
        stack.removeAt(stack.lastIndex)

        if (onesConnectedToBorder[currentRow][currentCol]) continue

        onesConnectedToBorder[currentRow][currentCol] = true

        for (neighbor in getNeighbors(currentRow, currentCol, matrix)) {
            val (neighborRow, neighborCol) = neighbor

            if (matrix[neighborRow][neighborCol] != 1) continue

            stack.add(neighbor)
        }
    }
}

fun getNeighbors(r: Int, c: Int, matrix: List<MutableList<Int>>): List<Pair<Int, Int>> {
    return listOf(r - 1 to c, r to c - 1, r + 1 to c, r to c + 1).filter { (row, col) ->
        row >= 0 && col >= 0 && row < matrix.size && col < matrix[0].size
    }
}