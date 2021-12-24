package ae.veryHard.mergeSort.solution2

fun mergeSort(array: MutableList<Int>): List<Int> {
    if (array.isEmpty()) return array
    // Copy the array to an auxiliary array, not to overwrite the original array in the sort process
    val auxiliaryArray = array.toMutableList()
    mergeSortHelper(array, 0, array.lastIndex, auxiliaryArray)
    return array
}

fun mergeSortHelper(mainArray: MutableList<Int>, startIdx: Int, endIdx: Int, auxiliaryArray: MutableList<Int>) {
    if (startIdx == endIdx) return

    val middleIdx = (startIdx + endIdx) / 2

    mergeSortHelper(auxiliaryArray, startIdx, middleIdx, mainArray)
    mergeSortHelper(auxiliaryArray, middleIdx + 1, endIdx, mainArray)

    mergeSortedArrays(mainArray, startIdx, middleIdx, endIdx, auxiliaryArray)
}

fun mergeSortedArrays(mainArray: MutableList<Int>, startIdx: Int, middleIdx: Int, endIdx: Int, auxiliaryArray: MutableList<Int>) {
    var i = startIdx
    var j = middleIdx + 1
    var k = startIdx

    while (i <= middleIdx && j <= endIdx) {
        if (auxiliaryArray[i] < auxiliaryArray[j]) {
            mainArray[k] = auxiliaryArray[i]
            i += 1
            k += 1
        } else {
            mainArray[k] = auxiliaryArray[j]
            j += 1
            k += 1
        }
    }

    while (i <= middleIdx) {
        mainArray[k] = auxiliaryArray[i]
        i += 1
        k += 1
    }

    while (j <= endIdx) {
        mainArray[k] = auxiliaryArray[j]
        j += 1
        k += 1
    }
}