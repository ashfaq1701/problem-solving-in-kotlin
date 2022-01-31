package ctci.nQueens.solution1

import kotlin.math.abs

class Solution {
    fun solveNQueens(n: Int): List<List<String>> {
        val queenPlacements = MutableList(n) { -1 }
        val result = mutableListOf<List<String>>()
        findNonAttackingQueenPlacements(0, n, queenPlacements, result)
        return result
    }

    fun findNonAttackingQueenPlacements(row: Int, n: Int, queenPlacements: MutableList<Int>, result: MutableList<List<String>>) {
        if (row == n) {
            result.add(convertToPlacementConfigurations(n, queenPlacements))
        }

        for (col in 0 until n) {
            if (isNonAttackingQueenPlacement(row, col, queenPlacements)) {
                queenPlacements[row] = col
                findNonAttackingQueenPlacements(row + 1, n, queenPlacements, result)
            }
        }
    }

    fun isNonAttackingQueenPlacement(row: Int, col: Int, queenPlacements: List<Int>): Boolean {
        for (prevRow in 0 until row) {
            val prevCol = queenPlacements[prevRow]

            if (col == prevCol) return false

            if (abs(col - prevCol) == row - prevRow) return false
        }

        return true
    }

    fun convertToPlacementConfigurations(n: Int, queenPlacements: List<Int>): List<String> {
        val configurations = mutableListOf<String>()

        for (placement in queenPlacements) {
            val row = MutableList<Char>(n) { '.' }
            row[placement] = 'Q'
            configurations.add(String(row.toCharArray()))
        }

        return configurations
    }
}