package ae.hard.indexEqualsValue.solution3

fun indexEqualsValue(array: List<Int>): Int {
    var left = 0
    var right = array.lastIndex

    while (left <= right) {
        val mid = (left + right) / 2

        if (array[mid] < mid) {
            left = mid + 1
        } else if (array[mid] == mid && mid == 0) {
            return mid
        } else if (array[mid] == mid && array[mid - 1] < mid - 1) {
            return mid
        } else {
            right = mid - 1
        }
    }

    return -1
}
