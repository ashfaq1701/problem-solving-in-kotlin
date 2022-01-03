package ae.hard.minNumberOfJumps.solution2

import kotlin.math.max

fun minNumberOfJumps(array: List<Int>): Int {

    if (array.size == 1) return 0

    var jumps = 0
    var maxReach = array[0]
    var steps = array[0]

    for (i in 1 until array.lastIndex) {
        maxReach = max(maxReach, i + array[i])
        steps -= 1

        if (steps == 0) {
            jumps += 1
            steps = maxReach - i
        }
    }

    return jumps + 1
}
