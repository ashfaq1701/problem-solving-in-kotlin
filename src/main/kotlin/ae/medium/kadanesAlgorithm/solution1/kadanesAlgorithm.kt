package ae.medium.kadanesAlgorithm.solution1

import kotlin.math.max

// maxEndingHere = 3, maxSoFar = 3
//  3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4
//  5 - maxEndingHere =  8, maxSoFar =  8
// -9 - maxEndingHere = -1, maxSoFar =  8
//  1 - maxEndingHere =  1, maxSoFar =  8
//  3 - maxEndingHere =  4, maxSoFar =  8
// -2 - maxEndingHere =  2, maxSoFar =  8
//  3 - maxEndingHere =  5, maxSoFar =  8
//  4 - maxEndingHere =  9, maxSoFar =  9
//  7 - maxEndingHere = 16, maxSoFar = 16
//  2 - maxEndingHere = 18, maxSoFar = 18
// -9 - maxEndingHere =  9, maxSoFar = 18
//  6 - maxEndingHere = 15, maxSoFar = 18
//  3 - maxEndingHere = 18, maxSoFar = 18
//  1 - maxEndingHere = 19, maxSoFar = 19
// -5 - maxEndingHere = 14, maxSoFar = 19
//  4 - maxEndingHere = 18, maxSoFar = 19
//  return 19

fun kadanesAlgorithm(array: List<Int>): Int {
    var maxEndingHere = array[0]
    var maxSoFar = array[0]

    for (i in 1 .. array.lastIndex) {
        maxEndingHere = max(array[i], maxEndingHere + array[i])
        maxSoFar = max(maxEndingHere, maxSoFar)
    }
    return maxSoFar
}
