package facebook.portal.seatingArrangements.solution1

import java.util.LinkedList
import kotlin.math.abs

fun main(args : Array<String>) {
    // Sorted - 5 6 8 10
    // Final  - 8 5 6 10
    // Sorted - 1 2 3 5 7
    // Final  - 7 3 1 2 5

    println(minOverallAwkwardness(arrayOf(1, 2, 5, 3, 7)))
}

fun minOverallAwkwardness(arr: Array<Int>): Int {
    val sortedHeights = arr.sorted()

    val qu = LinkedList<Int>()
    var isFront = true

    for (height in sortedHeights) {
        if (isFront) {
            qu.addFirst(height)
        } else {
            qu.addLast(height)
        }

        isFront = !isFront
    }

    var first = -1
    var prev = -1

    var awk = 0

    while (qu.isNotEmpty()) {
        val current = qu.poll()

        if (first == -1) {
            first = current
        }

        if (prev != -1) {
            awk += (current - prev)
        }

        prev = current
    }

    awk += prev - first

    return abs(awk)
}