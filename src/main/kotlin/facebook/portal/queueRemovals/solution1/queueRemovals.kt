package facebook.portal.queueRemovals.solution1

import java.util.LinkedList
import kotlin.math.max
import kotlin.math.min

fun main(args : Array<String>) {
    println(findPositions(arrayOf(1, 2, 2, 3, 4, 5), 5).toList())
    println(findPositions(arrayOf(2, 4, 2, 4, 3, 1, 2, 2, 3, 4, 3, 4, 4), 4).toList())
}

fun findPositions(arr: Array<Int>, x: Int): Array<Int> {
    val queue = LinkedList<Pair<Int, Int>>()

    arr.forEachIndexed { idx, value ->
        queue.add(idx to value)
    }

    return (0 until x).map {
        var greatestPolled = -1 to Int.MIN_VALUE
        val toPoll = min(x, queue.size)

        val polledItems = (0 until toPoll).map {
            val current = queue.poll()
            if (current.second > greatestPolled.second) {
                greatestPolled = current
            }
            current
        }

        polledItems.forEach { (idx, value) ->
            if (idx != greatestPolled.first) {
                queue.add(idx to max(value - 1, 0))
            }
        }

        greatestPolled.first + 1
    }.toTypedArray()
}