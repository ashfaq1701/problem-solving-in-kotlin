package ae.hard.indexEqualsValue.solution1

fun indexEqualsValue(array: List<Int>): Int {
    return indexWithValueHelper(array, 0, array.lastIndex)
}

fun indexWithValueHelper(array: List<Int>, left: Int, right: Int): Int {
    if (left > right) return -1

    val mid = (left + right) / 2

    return if (array[mid] < mid) {
        indexWithValueHelper(array, mid + 1, right)
    } else if (array[mid] > mid) {
        indexWithValueHelper(array, left, mid - 1)
    } else {
        if (mid == 0 || array[mid - 1] < mid - 1) {
            mid
        } else {
            indexWithValueHelper(array, left, mid - 1)
        }
    }
}
