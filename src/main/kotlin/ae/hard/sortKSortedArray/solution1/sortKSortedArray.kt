package ae.hard.sortKSortedArray.solution1

import java.util.PriorityQueue
import kotlin.math.min

fun sortKSortedArray(array: MutableList<Int>, k: Int): MutableList<Int> {
    val minHeapWithKElements = PriorityQueue<Int>()

    // At each index we need to check k elements on it's right
    // Including itself, k + 1 elements as potential candidate
    // to be placed in this index.
    for (i in 0 until min(k + 1, array.size)) {
        minHeapWithKElements.add(array[i])
    }

    var nextIndexToInsertElement = 0

    for (i in k + 1 .. array.lastIndex) {
        // We get the min element fron the heap of k + 1 size
        // And insert into the result array
        val minElement = minHeapWithKElements.poll()
        array[nextIndexToInsertElement] = minElement
        nextIndexToInsertElement += 1

        // And after that we push the current element
        // from the array into the heap to keep the heap
        // k + 1 elements long
        val currentElement = array[i]
        minHeapWithKElements.add(currentElement)
    }

    while (minHeapWithKElements.isNotEmpty()) {
        val minElement = minHeapWithKElements.poll()
        array[nextIndexToInsertElement] = minElement
        nextIndexToInsertElement += 1
    }

    return array
}
