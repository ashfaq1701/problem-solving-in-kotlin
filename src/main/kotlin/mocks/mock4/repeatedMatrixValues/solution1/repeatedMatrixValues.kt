package mocks.mock4.repeatedMatrixValues.solution1

fun repeatedMatrixValues(matrix: List<List<Int>>): List<Int> {
    var combinedSet = getElementSet(matrix[0])

    for (r in 1 .. matrix.lastIndex) {
        val row = matrix[r]
        val rowSet = getElementSet(row)
        combinedSet = filterElementsSet(combinedSet, rowSet)
    }

    for (c in 0 until matrix[0].size) {
        val col = mutableListOf<Int>()
        for (r in matrix.indices) {
            col.add(matrix[r][c])
        }

        val colSet = getElementSet(col)
        combinedSet = filterElementsSet(combinedSet, colSet)
    }

    return combinedSet.toList()
}

fun getElementSet(elements: List<Int>): MutableSet<Int> {
    return elements.toHashSet().toMutableSet()
}

fun filterElementsSet(elementSet: MutableSet<Int>, currentSet: Set<Int>): MutableSet<Int> {
    return elementSet.filter { currentSet.contains(it) }.toMutableSet()
}
