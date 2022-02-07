package ctci.findWinnerOnATicTacToeGame.solution1

class Solution {
    fun tictactoe(moves: Array<IntArray>): String {

        val board = MutableList(3) { MutableList(3) { 0 } }
        var currentPlayer = 1

        for ((moveR, moveC) in moves) {
            board[moveR][moveC] = currentPlayer

            if (solvedByRow(board, currentPlayer, moveR) ||
                solvedByCol(board, currentPlayer, moveC) ||
                solvedByDiagonal(board, currentPlayer) ||
                solvedByAntiDiagonal(board, currentPlayer)) {

                return if (currentPlayer == 1) "A" else "B"
            }

            currentPlayer *= -1
        }

        return if (moves.size == 9) "Draw" else "Pending"
    }

    fun solvedByRow(board: MutableList<MutableList<Int>>, value: Int, row: Int): Boolean {
        for (currentValue in board[row]) {
            if (currentValue != value) return false
        }

        return true
    }

    fun solvedByCol(board: MutableList<MutableList<Int>>, value: Int, col: Int): Boolean {
        for (row in board) {
            if (row[col] != value) return false
        }

        return true
    }

    fun solvedByDiagonal(board: MutableList<MutableList<Int>>, value: Int): Boolean {
        for (r in 0 until board.size) {
            if (board[r][r] != value) return false
        }

        return true
    }

    fun solvedByAntiDiagonal(board: MutableList<MutableList<Int>>, value: Int): Boolean {
        val n = board.size

        for (r in 0 until board.size) {
            if (board[r][n - 1 - r] != value) return false
        }

        return true
    }
}