package ae.medium.riverSizes.solution1

fun riverSizes(matrix: List<List<Int>>): List<Int> {
    val riverSizes = mutableListOf<Int>()

    val rows = matrix.size
    val cols = matrix[0].size
    val visited = MutableList(rows) { MutableList(cols) { false } }

    for (r in 0 until rows) {
        for (c in 0 until cols) {
            if (visited[r][c]) {
                continue
            }

            visitNode(r, c, matrix, visited, riverSizes)
        }
    }

    return riverSizes
}

fun visitNode(r: Int, c: Int, graph: List<List<Int>>, visited: MutableList<MutableList<Boolean>>, riverSizes: MutableList<Int>) {
    var currentRiverSize = 0

    val stack = mutableListOf<Pair<Int, Int>>()
    stack.add(r to c)

    while (stack.isNotEmpty()) {
        val (currentRow, currentCol) = stack.last()
        stack.removeAt(stack.lastIndex)

        if (visited[currentRow][currentCol]) continue

        if (graph[currentRow][currentCol] == 0) continue

        visited[currentRow][currentCol] = true
        currentRiverSize += 1

        for (neighbor in getUnvisitedNeighbors(currentRow, currentCol, visited)) {
            stack.add(neighbor)
        }
    }

    if (currentRiverSize > 0) {
        riverSizes.add(currentRiverSize)
    }
}

fun getUnvisitedNeighbors(r: Int, c: Int, visited: MutableList<MutableList<Boolean>>): List<Pair<Int, Int>> {
    return listOf(r - 1 to c, r to c - 1, r + 1 to c, r to c + 1).filter { (row, col) ->
        row >= 0 && col >= 0 && row < visited.size && col < visited[0].size && !visited[row][col]
    }
}
