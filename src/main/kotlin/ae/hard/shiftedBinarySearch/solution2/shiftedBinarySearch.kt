package ae.hard.shiftedBinarySearch.solution2

fun shiftedBinarySearch(array: List<Int>, target: Int): Int {
    var left = 0
    var right = array.lastIndex

    while (left <= right) {

        val middle = (left + right) / 2
        val potentialMatch = array[middle]
        val leftNum = array[left]
        val rightNum = array[right]

        if (target == potentialMatch) {
            return middle
        } else if (leftNum <= potentialMatch) {
            // Left half of the array is sorted
            if (target in leftNum until potentialMatch) {
                right = middle - 1
            } else {
                left = middle + 1
            }
        } else {
            // Right half of the array is sorted
            if (target in (potentialMatch + 1)..rightNum) {
                left = middle + 1
            } else {
                right = middle - 1
            }
        }
    }

    return -1
}
