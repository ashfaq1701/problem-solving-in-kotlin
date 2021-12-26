package ae.medium.monotonicArray.solution2

fun isMonotonic(array: List<Int>): Boolean {
    var isIncreasing = true
    var isDecreasing = true

    for (i in 1 .. array.lastIndex) {
        if (array[i] > array[i - 1]) {
            isDecreasing = false
        }

        if (array[i] < array[i - 1]) {
            isIncreasing = false
        }
    }

    return isIncreasing || isDecreasing
}
