package mocks.mock4.repeatedMatrixValues.solution2

fun repeatedMatrixValues(matrix: List<List<Int>>): List<Int> {
    val valueCounts = initializeCountsOfPotentialValues(matrix)

    for (r in matrix.indices) {
        for (c in matrix[0].indices) {
            val value = matrix[r][c]
            val expectedCountSoFar = r
            checkAndIncrementValueCount(value, valueCounts, expectedCountSoFar)
        }
    }

    for (c in matrix[0].indices) {
        for (r in matrix.indices) {
            val value = matrix[r][c]
            val expectedCountSoFar = matrix.size + c
            checkAndIncrementValueCount(value, valueCounts, expectedCountSoFar)
        }
    }

    val finalValues = mutableListOf<Int>()
    for ((value, count) in valueCounts) {
        if (count == matrix.size + matrix[0].size) {
            finalValues.add(value)
        }
    }

    return finalValues
}

fun initializeCountsOfPotentialValues(matrix: List<List<Int>>): MutableMap<Int, Int> {
    var smallerSide = matrix[0]
    if (matrix.size < matrix[0].size) {
        smallerSide = matrix.map { it[0] }
    }

    return smallerSide.associateWith { 0 }.toMutableMap()
}

fun checkAndIncrementValueCount(value: Int, valueCounts: MutableMap<Int, Int>, expectedCountSoFar: Int) {
    if (value !in valueCounts) return

    if (valueCounts[value] != expectedCountSoFar) return

    valueCounts[value] = valueCounts[value]!! + 1
}
