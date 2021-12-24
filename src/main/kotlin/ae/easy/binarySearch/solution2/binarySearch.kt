package ae.easy.binarySearch.solution2

fun binarySearch(array: List<Int>, target: Int): Int {
    var left = 0
    var right = array.lastIndex

    while (left <= right) {
        val mid = (left + right) / 2
        val potentialMatch = array[mid]

        if (target == potentialMatch) {
            return mid
        } else if (target < potentialMatch) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }

    return -1
}
