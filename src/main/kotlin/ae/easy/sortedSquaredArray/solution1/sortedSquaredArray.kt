package ae.easy.sortedSquaredArray.solution1

import kotlin.math.abs
import kotlin.math.pow

fun sortedSquaredArray(array: List<Int>): List<Int> {
    var left = 0
    var right = array.lastIndex

    val result = MutableList(array.size) { 0 }
    // Need to insert RIGHT TO LEFT, otherwise it will not work
    var resultIdx = array.lastIndex

    while (left <= right) {
        if (abs(array[right]) > abs(array[left])) {
            result[resultIdx] = array[right].toDouble().pow(2).toInt()
            right -= 1
        } else {
            result[resultIdx] = array[left].toDouble().pow(2).toInt()
            left += 1
        }

        resultIdx -= 1
    }

    return result
}
