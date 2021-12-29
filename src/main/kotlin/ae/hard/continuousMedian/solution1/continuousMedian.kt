package ae.hard.continuousMedian.solution1

import java.util.PriorityQueue

open class ContinuousMedianHandler() {
    // Write your code here.
    private var median: Double? = null

    val lowers = PriorityQueue<Int>(Comparator<Int> { i1, i2 ->
        i2.compareTo(i1)
    })
    val greaters = PriorityQueue<Int>()

    fun insert(number: Int) {
        if (lowers.isEmpty() || number < lowers.peek()) {
            lowers.add(number)
        } else {
            greaters.add(number)
        }

        rebalanceHeaps()
        updateMedian()
    }

    private fun rebalanceHeaps() {
        if (lowers.size - greaters.size == 2) {
            greaters.add(lowers.poll())
        } else if (greaters.size - lowers.size == 2) {
            lowers.add(greaters.poll())
        }
    }

    private fun updateMedian() {
        if (lowers.size == greaters.size) {
            median = (lowers.peek() + greaters.peek()) / 2.0
        } else if (lowers.size > greaters.size) {
            median = lowers.peek().toDouble()
        } else {
            median = greaters.peek().toDouble()
        }
    }

    fun getMedian(): Double? {
        return this.median
    }
}
