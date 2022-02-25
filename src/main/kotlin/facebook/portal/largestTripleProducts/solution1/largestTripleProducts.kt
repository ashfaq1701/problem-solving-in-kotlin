package facebook.portal.largestTripleProducts.solution1

import java.util.*
import kotlin.Comparator

fun main(args : Array<String>) {
    println(findMaxProduct(arrayOf(2, 1, 2, 1, 2)).toList())
}

fun findMaxProduct(args: Array<Int>): Array<Int> {
    val heap = PriorityQueue<Int>(Comparator<Int> { a, b ->
        b.compareTo(a)
    })

    return args.map { current ->
        heap.add(current)

        if (heap.size < 3) {
            -1
        } else {
            val a = heap.poll()
            val b = heap.poll()
            val c = heap.poll()

            heap.add(a)
            heap.add(b)
            heap.add(c)

            a * b * c
        }
    }.toTypedArray()
}