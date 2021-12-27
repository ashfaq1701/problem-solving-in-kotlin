package ae.medium.threeNumberSort.solution2

fun threeNumberSort(array: MutableList<Int>, order: List<Int>): List<Int> {
    val mutableArray = array.toMutableList()

    val (firstValue, _, thirdValue) = order

    var firstIdx = 0
    for (i in 0 .. mutableArray.lastIndex) {
        if (mutableArray[i] == firstValue) {
            swap(mutableArray, i, firstIdx)
            firstIdx += 1
        }
    }

    var thirdIdx = mutableArray.lastIndex
    for (i in mutableArray.lastIndex downTo 0) {
        if (mutableArray[i] == thirdValue) {
            swap(mutableArray, i, thirdIdx)
            thirdIdx -= 1
        }
    }

    return mutableArray
}

fun swap(array: MutableList<Int>, i: Int, j: Int) {
    array[i] = array[j].also {
        array[j] = array[i]
    }
}

