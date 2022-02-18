package kickStart.y2022.practiceSession1.hex

import kotlin.math.abs

fun parseAndCalculateHexWinner() {
    val numTests = readLine()!!.toInt()

    (1 .. numTests).toList().forEach {
        val boardSize = readLine()!!.toInt()

        val board = (0 until boardSize).toList().map {
            readLine()!!
        }

        println("Case #$it: " + findWinner(board, boardSize))
    }
}

fun findWinner(board: List<String>, boardSize: Int): String {

    var totalBlueStones = 0
    var totalRedStones = 0

    for (r in 0 until boardSize) {
        for (c in 0 until boardSize) {
            if (board[r][c] == 'B') {
                totalBlueStones += 1
            }

            if (board[r][c] == 'R') {
                totalRedStones += 1
            }
        }
    }

    if (abs(totalBlueStones - totalRedStones) > 1) return "Impossible"

    val blueReachedRight = ReachedOtherEnd()
    var visited = mutableSetOf<String>()
    board.forEachIndexed { rowIdx, row ->
        if (row[0] == 'B') {
            dfsTraverse(rowIdx, 0, board, boardSize, visited, blueReachedRight, Direction.HORIZONTAL)
        }
    }

    val redReachedBottom = ReachedOtherEnd()
    visited = mutableSetOf<String>()
    board[0].forEachIndexed { colIdx, col ->
        if (col == 'R') {
            dfsTraverse(0, colIdx, board, boardSize, visited, redReachedBottom, Direction.VERTICAL)
        }
    }

    return when {
        blueReachedRight.count + redReachedBottom.count > 1 -> "Impossible"
        blueReachedRight.count == 1 -> "Blue wins"
        redReachedBottom.count == 1 -> "Red wins"
        else -> "Nobody wins"
    }
}

fun dfsTraverse(row: Int, col: Int, board: List<String>, boardSize: Int, visited: MutableSet<String>, reachedOtherEnd: ReachedOtherEnd, direction: Direction) {
    val cellKey = getCellKey(row, col)
    if (cellKey in visited) return

    visited.add(cellKey)

    if (direction == Direction.HORIZONTAL && col == boardSize - 1) {
        reachedOtherEnd.count += 1
        return
    }

    if (direction == Direction.VERTICAL && row == boardSize - 1) {
        reachedOtherEnd.count += 1
        return
    }

    val targetCellValue = if (direction == Direction.HORIZONTAL) 'B' else 'R'
    for ((adjRow, adjCol) in getNeighbors(row, col, boardSize)) {
        if (board[adjRow][adjCol] == targetCellValue && getCellKey(adjRow, adjCol) !in visited) {
            dfsTraverse(adjRow, adjCol, board, boardSize, visited, reachedOtherEnd, direction)
        }
    }
}

fun getNeighbors(row: Int, col: Int, boardSize: Int): List<Pair<Int, Int>> {
    return listOf(
        row - 1 to col,
        row + 1 to col,
        row to col - 1,
        row to col + 1,
        row - 1 to col + 1,
        row + 1 to col - 1
    ).filter { (r, c) ->
        r in 0 until boardSize && c in 0 until boardSize
    }
}

fun getCellKey(row: Int, col: Int) = "$row-$col"

class ReachedOtherEnd(var count: Int = 0)

enum class Direction { HORIZONTAL, VERTICAL }