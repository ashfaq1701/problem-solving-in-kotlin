package ctci.findWinnerOnATicTacToeGame.solution2

import kotlin.math.abs

class Solution {
    fun tictactoe(moves: Array<IntArray>): String {
        val rows = MutableList(3) { 0 }
        val cols = MutableList(3) { 0 }
        var diag = 0
        var antiDiag = 0

        var currentPlayer = 1

        for ((row, col) in moves) {

            rows[row] += currentPlayer
            cols[col] += currentPlayer

            if (row == col) {
                diag += currentPlayer
            }

            if (row + col == 2) {
                antiDiag += currentPlayer
            }

            if (abs(rows[row]) == 3 || abs(cols[col]) == 3 || abs(diag) == 3 || abs(antiDiag) == 3) {
                return if (currentPlayer == 1) "A" else "B"
            }

            currentPlayer *= -1
        }

        return if (moves.size == 9) "Draw" else "Pending"
    }
}