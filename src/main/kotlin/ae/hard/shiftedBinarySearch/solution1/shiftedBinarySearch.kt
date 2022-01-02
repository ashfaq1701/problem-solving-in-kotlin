package ae.hard.shiftedBinarySearch.solution1

fun shiftedBinarySearch(array: List<Int>, target: Int): Int {
    return binarySearchHelper(array, target, 0, array.lastIndex)
}

fun binarySearchHelper(array: List<Int>, target: Int, left: Int, right:Int): Int {
    if (left > right) return -1

    val middle = (left + right) / 2
    val potentialMatch = array[middle]
    val leftNum = array[left]
    val rightNum = array[right]

    return if (target == potentialMatch) {
        middle
    } else if (leftNum <= potentialMatch) {
        // Left half of the array is sorted
        if (target in leftNum until potentialMatch) {
            binarySearchHelper(array, target, left, middle - 1)
        } else {
            binarySearchHelper(array, target, middle + 1, right)
        }
    } else {
        // Right half of the array is sorted
        if (target in (potentialMatch + 1)..rightNum) {
            binarySearchHelper(array, target, middle + 1, right)
        } else {
            binarySearchHelper(array, target, left, middle - 1)
        }
    }
}
