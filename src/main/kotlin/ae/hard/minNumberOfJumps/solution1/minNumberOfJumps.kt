package ae.hard.minNumberOfJumps.solution1

import kotlin.math.min

fun minNumberOfJumps(array: List<Int>): Int {
    val jumps = MutableList(array.size) { Int.MAX_VALUE }
    jumps[0] = 0

    for (i in 0 until array.size) {
        for (j in 0 until i) {
            if (j + array[j] >= i) {
                jumps[i] = min(jumps[i], jumps[j] + 1)
            }
        }
    }

    return jumps.last()
}
