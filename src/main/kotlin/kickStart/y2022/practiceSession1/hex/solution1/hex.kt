package kickStart.y2022.practiceSession1.hex.solution1

import kotlin.math.abs

fun parseAndCalculateHexWinner() {
    val numTests = readLine()!!.toInt()

    (1 .. numTests).toList().forEach {
        val boardSize = readLine()!!.toInt()

        val board = (0 until boardSize).toList().map {
            readLine()!!.toCharArray()
        }

        println("Case #$it: " + getBoardResult(board))
    }
}

fun getBoardResult(board: List<CharArray>): String {
    val (blueStoneCount, redStoneCount) = countStones(board)

    if (abs(blueStoneCount - redStoneCount) > 1) return "Impossible"

    val copiedBoard = board.map { it.toList().toCharArray() }
    val winner = getWinner(copiedBoard)

    if (winner == '.') return "Nobody wins"

    if ((winner == 'B' && redStoneCount > blueStoneCount) || (winner == 'R' && blueStoneCount > redStoneCount)) {
        return "Impossible"
    }

    val boardSize = board.size
    for (row in 0 until boardSize) {
        for (col in 0 until boardSize) {
            if (board[row][col] == winner) {
                val innerCopiedBoard = board.map { it.toList().toCharArray() }
                innerCopiedBoard[row][col] = '.'
                val winnerRemovingStone = getWinner(innerCopiedBoard)
                if (winnerRemovingStone == '.') {
                    return if (winner == 'B') "Blue wins" else "Red wins"
                }
            }
        }
    }

    return "Impossible"
}

fun getWinner(board: List<CharArray>): Char {
    val boardSize = board.size

    for (idx in 0 until boardSize) {
        if (board[idx][0] == 'B') {
            floodFill(board, idx, 0, 'B')
        }

        if (board[0][idx] == 'R') {
            floodFill(board, 0, idx, 'R')
        }
    }

    for (idx in 0 until boardSize) {
        if (board[idx][boardSize - 1] == 'b') {
            return 'B'
        }

        if (board[boardSize - 1][idx] == 'r') {
            return 'R'
        }
    }

    return '.'
}

fun floodFill(board: List<CharArray>, row: Int, col: Int, color: Char) {
    if (board[row][col] != color) return

    board[row][col] = color.toLowerCase()

    for ((adjRow, adjCol) in getNeighbors(row, col, board.size)) {
        if (board[adjRow][adjCol] == color) {
            floodFill(board, adjRow, adjCol, color)
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
    ).filter { (adjR, adjC) ->
        adjR in 0 until boardSize && adjC in 0 until boardSize
    }
}

fun countStones(board: List<CharArray>): Pair<Int, Int> {
    var blueCount = 0
    var redCount = 0

    for (row in board) {
        for (stone in row) {
            if (stone == 'B') {
                blueCount += 1
            } else if (stone == 'R') {
                redCount += 1
            }
        }
    }

    return blueCount to redCount
}

fun main() {
    parseAndCalculateHexWinner()
}