package ae.hard.searchForRange.solution1

fun searchForRange(array: List<Int>, target: Int): List<Int> {
    val range = mutableListOf(-1, -1)
    alteredBinarySearch(array, target, 0, array.lastIndex, range, true)
    alteredBinarySearch(array, target, 0, array.lastIndex, range, false)
    return range
}

fun alteredBinarySearch(array: List<Int>, target: Int, left: Int, right: Int, range: MutableList<Int>, goLeft: Boolean) {
    if (left > right) return

    val mid = (left + right) / 2

    if (target < array[mid]) {
        alteredBinarySearch(array, target, left, mid - 1, range, goLeft)
    } else if (target > array[mid]) {
        alteredBinarySearch(array, target, mid + 1, right, range, goLeft)
    } else {
        if (goLeft) {
            if (mid == 0 || array[mid - 1] != target) {
                range[0] = mid
            } else {
                alteredBinarySearch(array, target, left, mid - 1, range, goLeft)
            }
        } else {
            if (mid == array.lastIndex || array[mid + 1] != target) {
                range[1] = mid
            } else {
                alteredBinarySearch(array, target, mid + 1, right, range, goLeft)
            }
        }
    }
}
