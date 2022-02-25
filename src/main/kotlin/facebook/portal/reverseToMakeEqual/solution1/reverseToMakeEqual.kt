package facebook.portal.reverseToMakeEqual.solution1

import kotlin.test.assertEquals

fun main(args : Array<String>) {
    assertEquals(areTheyEqual(arrayOf(1, 2, 3, 4), arrayOf(1, 4, 3, 2)), true)
}

fun areTheyEqual(arr_a: Array<Int>, arr_b: Array<Int>): Boolean {
    val aCounts = countElements(arr_a)
    val bCounts = countElements(arr_b)
    return compareMaps(aCounts, bCounts)
}

fun countElements(arr: Array<Int>): Map<Int, Int> {
    val counts = mutableMapOf<Int, Int>()

    for (element in arr) {
        if (element !in counts) {
            counts[element] = 0
        }
        counts[element] = counts[element]!! + 1
    }

    return counts
}

fun compareMaps(mapOne: Map<Int, Int>, mapTwo: Map<Int, Int>): Boolean {
    if (mapOne.size != mapTwo.size) return false

    for ((element, count) in mapOne) {
        if (element !in mapTwo || mapTwo[element]!! != count) {
            return false
        }
    }

    return true
}