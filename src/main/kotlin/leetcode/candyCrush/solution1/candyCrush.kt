package leetcode.candyCrush.solution1

import kotlin.math.abs

class Solution {
    fun candyCrush(board: Array<IntArray>): Array<IntArray> {
        val rows = board.size
        val cols = board[0].size

        var toCrush = false

        for (r in 0 until board.size - 2) {
            for (c in board[0].indices) {
                val currentValue = abs(board[r][c])

                if (currentValue == 0) continue

                if (abs(board[r + 1][c]) == currentValue && abs(board[r + 2][c]) == currentValue) {
                    toCrush = true

                    board[r][c] = -currentValue
                    board[r + 1][c] = -currentValue
                    board[r + 2][c] = -currentValue
                }
            }
        }

        for (r in board.indices) {
            for (c in 0 until board[0].size - 2) {
                val currentValue = abs(board[r][c])

                if (currentValue == 0) continue

                if (abs(board[r][c + 1]) == currentValue && abs(board[r][c + 2]) == currentValue) {
                    toCrush = true

                    board[r][c] = -currentValue
                    board[r][c + 1] = -currentValue
                    board[r][c + 2] = -currentValue
                }
            }
        }

        for (c in board[0].indices) {
            var writePtr = board.lastIndex

            for (r in board.indices.reversed()) {
                if (board[r][c] > 0) {
                    board[writePtr][c] = board[r][c]
                    writePtr -= 1
                }
            }

            for (r in writePtr downTo 0) {
                board[r][c] = 0
            }
        }

        return if (toCrush) candyCrush(board) else board
    }
}