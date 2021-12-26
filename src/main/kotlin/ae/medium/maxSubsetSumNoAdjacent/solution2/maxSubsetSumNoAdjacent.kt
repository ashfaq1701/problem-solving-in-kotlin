package ae.medium.maxSubsetSumNoAdjacent.solution2

import kotlin.math.max

fun maxSubsetSumNoAdjacent(array: List<Int>): Int {
    if (array.isEmpty()) return 0
    if (array.size == 1) return array[0]

    var first = array[0]
    var second = max(array[0], array[1])

    for (i in 2 .. array.lastIndex) {
        second = max(second, first + array[i]).also {
            first = second
        }
    }

    return second
}
