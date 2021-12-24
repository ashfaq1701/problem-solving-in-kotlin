package ae.veryHard.nonAttackingQueens.solution2

// Time: O(n!) Upper Bound
fun nonAttackingQueens(n: Int): Int {
    val blockedColumns = mutableSetOf<Int>()
    val blockedUpDiagonals = mutableSetOf<Int>()
    val blockedDownDiagonals = mutableSetOf<Int>()
    return getNonAttackingQueenPlacements(0, blockedColumns, blockedUpDiagonals, blockedDownDiagonals, n)
}

fun getNonAttackingQueenPlacements(row: Int, blockedColumns: MutableSet<Int>, blockedUpDiagonals: MutableSet<Int>, blockedDownDiagonals: MutableSet<Int>, boardSize: Int): Int {
    // If we reached past number of total rows, then we are good, we have a non attacking queen placement.
    if (row == boardSize)
        return 1

    var nonAttackingPlacementCount = 0

    // For all column attempt to place the queen
    for (col in 0 until boardSize) {

        // If the queen placement in this column in non-attacking
        if (isNonAttackingPlacement(row, col, blockedColumns, blockedUpDiagonals, blockedDownDiagonals)) {

            // Place the queen in this col for this row
            placeQueen(row, col, blockedColumns, blockedUpDiagonals, blockedDownDiagonals)

            // Then try to place queen in next row and accumulate the "bubbled-up" non attacking placements for next row
            nonAttackingPlacementCount += getNonAttackingQueenPlacements(row + 1, blockedColumns, blockedUpDiagonals, blockedDownDiagonals, boardSize)

            // Backtrack, we need to clear the sets for current placement.
            // Otherwise for future placements we may mark future columns as blocked when they are not relevant (not blocked) anymore.
            removeQueen(row, col, blockedColumns, blockedUpDiagonals, blockedDownDiagonals)
        }
    }

    return nonAttackingPlacementCount
}

// Check if the queen placement in row and col is a not attacking placement
fun isNonAttackingPlacement(row: Int, col: Int, blockedColumns: MutableSet<Int>, blockedUpDiagonals: MutableSet<Int>, blockedDownDiagonals: MutableSet<Int>): Boolean {
    if (blockedColumns.contains(col)) {
        return false
    }

    if (blockedUpDiagonals.contains(row + col)) {
        return false
    }

    if (blockedDownDiagonals.contains(row - col)) {
        return false
    }

    return true
}

fun placeQueen(row: Int, col: Int, blockedColumns: MutableSet<Int>, blockedUpDiagonals: MutableSet<Int>, blockedDownDiagonals: MutableSet<Int>) {
    blockedColumns.add(col)
    blockedUpDiagonals.add(row + col)
    blockedDownDiagonals.add(row - col)
}

fun removeQueen(row: Int, col: Int, blockedColumns: MutableSet<Int>, blockedUpDiagonals: MutableSet<Int>, blockedDownDiagonals: MutableSet<Int>) {
    blockedColumns.remove(col)
    blockedUpDiagonals.remove(row + col)
    blockedDownDiagonals.remove(row - col)
}