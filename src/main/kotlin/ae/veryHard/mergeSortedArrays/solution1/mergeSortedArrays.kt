package ae.veryHard.mergeSortedArrays.solution1

import java.util.PriorityQueue

fun mergeSortedArrays(arrays: List<List<Int>>): List<Int> {
    val sortedList = mutableListOf<Int>()

    val heap = PriorityQueue<Item> { item1, item2 ->
        item1.elementValue - item2.elementValue
    }

    arrays.forEachIndexed { idx, array ->
        heap.add(Item(idx, 0, array[0]))
    }

    while (heap.isNotEmpty()) {

        val currentItem = heap.poll()

        val (arrayIdx, elementIdx, elementValue) = currentItem

        sortedList.add(elementValue)

        if (elementIdx < arrays[arrayIdx].lastIndex) {
            heap.add(Item(arrayIdx, elementIdx + 1, arrays[arrayIdx][elementIdx + 1]))
        }
    }

    return sortedList
}

data class Item(val arrayIdx: Int, val elementIdx: Int, val elementValue: Int)
