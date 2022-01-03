package ae.hard.maximizeExpression.solution2

import kotlin.math.max

fun maximizeExpression(array: List<Int>): Int {
    if (array.size < 4) return 0

    val maxOfA = MutableList(array.size) { 0 }
    maxOfA[0] = array[0]

    val maxOfAMinusB = MutableList(array.size) { 0 }
    maxOfAMinusB[0] = Int.MIN_VALUE

    val maxOfAMinusBPlusC = MutableList(array.size) { 0 }
    maxOfAMinusBPlusC[0] = Int.MIN_VALUE
    maxOfAMinusBPlusC[1] = Int.MIN_VALUE

    val maxOfAMinusBPlusCMinusD = MutableList(array.size) { 0 }
    maxOfAMinusBPlusCMinusD[0] = Int.MIN_VALUE
    maxOfAMinusBPlusCMinusD[1] = Int.MIN_VALUE
    maxOfAMinusBPlusCMinusD[2] = Int.MIN_VALUE

    for (a in 1 .. array.lastIndex) {
        val currentMax = max(maxOfA[a - 1], array[a])
        maxOfA[a] = currentMax
    }

    for (b in 1 .. array.lastIndex) {
        val currentMax = max(maxOfAMinusB[b - 1], maxOfA[b - 1] - array[b])
        maxOfAMinusB[b] = currentMax
    }

    for (c in 2 .. array.lastIndex) {
        val currentMax = max(maxOfAMinusBPlusC[c - 1], maxOfAMinusB[c - 1] + array[c])
        maxOfAMinusBPlusC[c] = currentMax
    }

    for (d in 3 .. array.lastIndex) {
        val currentMax = max(maxOfAMinusBPlusCMinusD[d - 1], maxOfAMinusBPlusC[d - 1] - array[d])
        maxOfAMinusBPlusCMinusD[d] = currentMax
    }

    return maxOfAMinusBPlusCMinusD.last()
}
