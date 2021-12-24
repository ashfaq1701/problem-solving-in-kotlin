package ae.easy.binarySearch.solution1

fun binarySearch(array: List<Int>, target: Int): Int {
    return binarySearchHelper(array, target, 0, array.lastIndex)
}

fun binarySearchHelper(array: List<Int>, target: Int, left: Int, right: Int): Int {
    if (left > right) return -1
    val mid = (left + right) / 2
    val potentialMatch = array[mid]

    return if (potentialMatch == target) {
        mid
    } else if (target < potentialMatch) {
        binarySearchHelper(array, target, left, mid - 1)
    } else {
        binarySearchHelper(array, target, mid + 1, right)
    }
}
