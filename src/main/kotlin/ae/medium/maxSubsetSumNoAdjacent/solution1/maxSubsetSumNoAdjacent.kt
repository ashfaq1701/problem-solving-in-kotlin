package ae.medium.maxSubsetSumNoAdjacent.solution1

import kotlin.math.max

fun maxSubsetSumNoAdjacent(array: List<Int>): Int {
    if (array.isEmpty()) return 0
    if (array.size == 1) return array[0]

    val maxSums = MutableList(array.size) { 0 }
    maxSums[0] = array[0]
    maxSums[1] = max(array[0], array[1])

    for (i in 2 .. array.lastIndex) {
        maxSums[i] = max(maxSums[i - 1], maxSums[i - 2] + array[i])
    }

    return maxSums.last()
}
