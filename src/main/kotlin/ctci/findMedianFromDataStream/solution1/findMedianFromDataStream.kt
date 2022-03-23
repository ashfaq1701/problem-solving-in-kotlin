package ctci.findMedianFromDataStream.solution1

import java.util.*
import kotlin.Comparator

class MedianFinder() {

    val lowers = PriorityQueue<Int>(Comparator<Int> { a, b ->
        b.compareTo(a)
    })

    val highers = PriorityQueue<Int>()

    fun addNum(num: Int) {
        lowers.add(num)
        highers.add(lowers.poll())

        if (lowers.size < highers.size) {
            lowers.add(highers.poll())
        }
    }

    fun findMedian(): Double {
        return when {
            lowers.size > highers.size -> lowers.peek().toDouble()
            highers.size > lowers.size -> highers.peek().toDouble()
            else -> (lowers.peek() + highers.peek()) / 2.0
        }
    }

}