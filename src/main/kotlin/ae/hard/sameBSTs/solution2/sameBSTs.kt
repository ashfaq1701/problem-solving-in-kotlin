package ae.hard.sameBSTs.solution2

fun sameBsts(arrayOne: List<Int>, arrayTwo: List<Int>): Boolean {
    return areSameBsts(arrayOne, arrayTwo, 0, 0, Int.MIN_VALUE, Int.MAX_VALUE)
}

fun areSameBsts(arrayOne: List<Int>, arrayTwo: List<Int>, rootIdxOne: Int, rootIdxTwo: Int, minVal: Int, maxVal: Int): Boolean {
    if (rootIdxOne == -1 || rootIdxTwo == -1) {
        return rootIdxOne == rootIdxTwo
    }

    if (arrayOne[rootIdxOne] != arrayTwo[rootIdxTwo]) {
        return false
    }

    val leftIdxOne = getIdxOfFirstSmaller(arrayOne, rootIdxOne, minVal)
    val leftIdxTwo = getIdxOfFirstSmaller(arrayTwo, rootIdxTwo, minVal)

    val rightIdxOne = getIdxOfFirstGreaterTnanOrEqual(arrayOne, rootIdxOne, maxVal)
    val rightIdxTwo = getIdxOfFirstGreaterTnanOrEqual(arrayTwo, rootIdxTwo, maxVal)

    val currentVal = arrayOne[rootIdxOne]
    val areLeftSame = areSameBsts(arrayOne, arrayTwo, leftIdxOne, leftIdxTwo, minVal, currentVal)
    val areRightSame = areSameBsts(arrayOne, arrayTwo, rightIdxOne, rightIdxTwo, currentVal, maxVal)

    return areLeftSame && areRightSame
}

fun getIdxOfFirstSmaller(array: List<Int>, rootIdx: Int, minVal: Int): Int {
    for (i in rootIdx + 1 .. array.lastIndex) {
        if (array[i] < array[rootIdx] && array[i] >= minVal) {
            return i
        }
    }

    return -1
}

fun getIdxOfFirstGreaterTnanOrEqual(array: List<Int>, rootIdx: Int, maxVal: Int): Int {
    for (i in rootIdx + 1 .. array.lastIndex) {
        if (array[i] >= array[rootIdx] && array[i] < maxVal) {
            return i
        }
    }

    return -1
}
