package ae.medium.monotonicArray.solution1

fun isMonotonic(array: List<Int>): Boolean {
    var arrayState = State.UNDETECTED

    for (i in 1 .. array.lastIndex) {
        if (arrayState == State.UNDETECTED) {
            if (array[i] > array[i - 1]) {
                arrayState = State.INCREASING
            }

            if (array[i] < array[i - 1]) {
                arrayState = State.DECREASING
            }
        }

        if (arrayState == State.INCREASING && array[i] < array[i - 1]) {
            return false
        }

        if (arrayState == State.DECREASING && array[i] > array[i - 1]) {
            return false
        }
    }

    return true
}

enum class State {
    UNDETECTED, INCREASING, DECREASING
}