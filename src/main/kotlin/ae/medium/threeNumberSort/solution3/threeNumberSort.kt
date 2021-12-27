package ae.medium.threeNumberSort.solution3

fun threeNumberSort(array: MutableList<Int>, order: List<Int>): List<Int> {

    val mutableArray = array.toMutableList()

    val (firstValue, secondValue, _) = order
    var firstIdx = 0
    var secondIdx = 0
    var thirdIdx = array.lastIndex

    while (secondIdx <= thirdIdx) {
        if (mutableArray[secondIdx] == firstValue) {
            swap(mutableArray, secondIdx, firstIdx)
            firstIdx += 1
            secondIdx += 1
        } else if (mutableArray[secondIdx] == secondValue) {
            secondIdx += 1
        } else {
            swap(mutableArray, secondIdx, thirdIdx)
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