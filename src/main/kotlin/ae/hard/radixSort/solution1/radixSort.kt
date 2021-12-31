package ae.hard.radixSort.solution1

import kotlin.math.pow

// O(d * (n + b)) Time | O(n + b) space
fun radixSort(array: MutableList<Int>): MutableList<Int> {

    if (array.isEmpty()) return array

    val max = array.maxOrNull()!!
    var position = 0

    // Position is the 10's place e.g.
    // For 210, 0 has position 0, 1 has 1 and 2 has 2
    while (max / 10.toDouble().pow(position) > 0) {
        countingSort(array, position)
        position += 1
    }

    return array
}

fun countingSort(array: MutableList<Int>, position: Int) {
    val counts = MutableList(10) { 0 }
    val sorted = MutableList(array.size) { 0 }

    val divider = 10.toDouble().pow(position).toInt()

    for (current in array) {
        val digit = (current / divider) % 10
        counts[digit] += 1
    }

    // We need to modify counts array counts[i] += counts[i - 1]
    // this way to find the occurance of last position of a digit
    // As example [ 0, 0, 3, 5, 7, 7, 8, 9, 9] means that
    // First time we see a 2 it will be at position 3 (index 2)
    // Second time we see a 2 it will be at position 2 (index 1)
    // Third time we see a 2 it will be at position 1 (index 0)
    for (i in 1 until 10) {
        counts[i] += counts[i - 1]
    }

    // We need to iterate backward while inserting elements
    // Is to keep the previous ordering of the
    // partial sorted array we've done before.
    // e.g when sorting by position 1
    // we need to keep the sorting done by position 0.
    for (i in array.lastIndex downTo 0) {
        val currentNum = array[i]
        val digit = (currentNum / divider) % 10
        counts[digit] -= 1
        val indexToInsert = counts[digit]
        sorted[indexToInsert] = currentNum
    }

    for (i in 0 .. array.lastIndex) {
        array[i] = sorted[i]
    }
}