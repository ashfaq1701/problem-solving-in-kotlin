package facebook.portal.magicalCandyBags.solution1

import java.util.*

fun main(args : Array<String>) {
    println(maxCandies(arrayOf(2, 1, 7, 4, 2), 3))
}

fun maxCandies(arr: Array<Int>, k: Int): Int {
    val heap = PriorityQueue<Int>(Comparator<Int> { a, b ->
        b.compareTo(a)
    })

    for (bag in arr) {
        heap.add(bag)
    }

    var minutesLeft = k
    var ateCandies = 0

    while (minutesLeft > 0) {
        val maxCandyBag = heap.poll()
        ateCandies += maxCandyBag
        heap.add(maxCandyBag / 2)
        minutesLeft -= 1
    }

    return ateCandies
}