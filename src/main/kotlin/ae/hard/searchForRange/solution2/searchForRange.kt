package ae.hard.searchForRange.solution2

fun searchForRange(array: List<Int>, target: Int): List<Int> {
    val range = mutableListOf(-1, -1)
    alteredBinarySearch(array, target, 0, array.lastIndex, range, true)
    alteredBinarySearch(array, target, 0, array.lastIndex, range, false)
    return range
}

fun alteredBinarySearch(array: List<Int>, target: Int, left: Int, right: Int, range: MutableList<Int>, goLeft: Boolean) {
    var leftIdx = left
    var rightIdx = right

    while (leftIdx <= rightIdx) {
        val mid = (leftIdx + rightIdx) / 2

        if (target < array[mid]) {
            rightIdx = mid - 1
        } else if (target > array[mid]) {
            leftIdx = mid + 1
        } else {
            if (goLeft) {
                if (mid == 0 || array[mid - 1] != target) {
                    range[0] = mid
                    return
                } else {
                    rightIdx = mid - 1
                }
            } else {
                if (mid == array.lastIndex || array[mid + 1] != target) {
                    range[1] = mid
                    return
                } else {
                    leftIdx = mid + 1
                }
            }
        }
    }
}
