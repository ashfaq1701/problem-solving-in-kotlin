package ae.hard.subarraySort.solution1

import kotlin.math.min
import kotlin.math.max

// Find the smallest and largest numbers that are out of order in the input array.
// Once the smallest and largest out of order numbers are found
// we need to find their final sorted positions.
// This will be the extremities of the smallest subarray that needs to be sorted.
fun subarraySort(array: List<Int>): List<Int> {
    var minOutOfOrder = Int.MAX_VALUE
    var maxOutOfOrder = Int.MIN_VALUE

    for (i in 0 .. array.lastIndex) {
        if (isOutOfOrder(i, array)) {
            minOutOfOrder = min(minOutOfOrder, array[i])
            maxOutOfOrder = max(maxOutOfOrder, array[i])
        }
    }

    if (minOutOfOrder == Int.MAX_VALUE) {
        return listOf(-1, -1)
    }

    var subarrayLeftIdx = 0
    while (array[subarrayLeftIdx] <= minOutOfOrder) {
        subarrayLeftIdx += 1
    }

    var subarrayRightIdx = array.lastIndex
    while (array[subarrayRightIdx] >= maxOutOfOrder) {
        subarrayRightIdx -= 1
    }

    return listOf(subarrayLeftIdx, subarrayRightIdx)
}

fun isOutOfOrder(idx: Int, array: List<Int>): Boolean {
    return when (idx) {
        0 -> array[0] > array[1]
        array.lastIndex -> array[array.lastIndex] < array[array.lastIndex - 1]
        else -> array[idx] < array[idx - 1] || array[idx] > array[idx + 1]
    }
}
