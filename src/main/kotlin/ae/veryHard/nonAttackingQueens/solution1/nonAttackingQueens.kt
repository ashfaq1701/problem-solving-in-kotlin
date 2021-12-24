package ae.veryHard.nonAttackingQueens.solution1

import kotlin.math.abs

// Time: O(n!) Lower Bound
fun nonAttackingQueens(n: Int): Int {
    val queenPlacements = MutableList(n) { 0 }
    return getNonAttackingQueenPlacements(0, queenPlacements, n)
}

fun getNonAttackingQueenPlacements(row: Int, queenPlacements: MutableList<Int>, boardSize: Int): Int {
    // If we reached past number of total rows, then we are good, we have a non attacking queen placement.
    if (row == boardSize)
        return 1

    var nonAttackingPlacementCount = 0

    // For all column attempt to place the queen
    for (col in 0 until boardSize) {

        // If the queen placement in this column in non-attacking
        if (isNonAttackingPlacement(row, col, queenPlacements)) {

            // Place the queen in this col for this row
            queenPlacements[row] = col

            // Then try to place queen in next row and accumulate the "bubbled-up" non attacking placements for next row
            nonAttackingPlacementCount += getNonAttackingQueenPlacements(row + 1, queenPlacements, boardSize)
        }
    }

    return nonAttackingPlacementCount
}

// Check if the queen placement in row and col is a not attacking placement
fun isNonAttackingPlacement(row: Int, col: Int, queenPlacements: MutableList<Int>): Boolean {

    // To determine if a placement is non-attacking,
    // we need to examine all the row above for potential conflicts
    for (r in 0 until row) {

        val placementColInRow = queenPlacements[r]

        val blockedByColumn = placementColInRow == col

        // Diagonal check by abs(r1 - r2) == abs(c1 - c2)
        val blockedByDiagonal = abs(placementColInRow - col) == row - r

        // If we get any conflict either in column or in diagonal, we then return false
        if (blockedByColumn || blockedByDiagonal)
            return false
    }

    return true
}
