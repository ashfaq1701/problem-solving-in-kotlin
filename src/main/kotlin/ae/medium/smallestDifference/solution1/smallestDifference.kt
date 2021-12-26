package ae.medium.smallestDifference.solution1

import kotlin.math.abs

fun smallestDifference(arrayOne: MutableList<Int>, arrayTwo: MutableList<Int>): List<Int> {
    arrayOne.sort()
    arrayTwo.sort()

    var i = 0
    var j = 0

    var minDiff = Integer.MAX_VALUE
    var minPair: List<Int> = listOf<Int>()

    while (i < arrayOne.size && j < arrayTwo.size) {
        val numOne = arrayOne[i]
        val numTwo = arrayTwo[j]

        if (numOne < numTwo) {
            i += 1
        } else if (numTwo < numOne) {
            j += 1
        } else {
            return listOf(numOne, numTwo)
        }

        if (abs(numOne - numTwo) < minDiff) {
            minDiff = abs(numOne - numTwo)
            minPair = listOf(numOne, numTwo)
        }
    }

    return minPair
}
