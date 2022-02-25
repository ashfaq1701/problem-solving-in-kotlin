package facebook.portal.medianStream.solution1

import java.util.*

fun main(args : Array<String>) {
    println(findMedian(arrayOf(5, 15, 1, 3)).toList())
}

fun findMedian(arr: Array<Int>): Array<Int> {
    val lowers = PriorityQueue<Int>(Comparator<Int> { a, b ->
        b.compareTo(a)
    })

    var greaters = PriorityQueue<Int>()

    return arr.map { current ->
        lowers.add(current)

        while (lowers.size - greaters.size > 1) {
            val maxLower = lowers.poll()
            greaters.add(maxLower)
        }

        if (lowers.size == greaters.size) {
            (lowers.peek() + greaters.peek()) / 2
        } else {
            if (lowers.size > greaters.size) lowers.peek() else greaters.peek()
        }

    }.toTypedArray()
}