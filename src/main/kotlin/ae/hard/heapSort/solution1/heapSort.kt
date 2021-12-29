package ae.hard.heapSort.solution1

fun heapSort(array: MutableList<Int>): List<Int> {
    buildHeap(array)
    for (i in array.lastIndex downTo 1) {
        swap(0, i, array)
        siftDown(0, i - 1, array)
    }
    return array
}

fun swap(i: Int, j: Int, array: MutableList<Int>) {
    array[i] = array[j].also {
        array[j] = array[i]
    }
}

fun buildHeap(array: MutableList<Int>) {
    val firstParentIdx = Math.floor((array.size - 1.0) / 2.0).toInt()
    for (i in firstParentIdx downTo 0) {
        siftDown(i, array.size - 1, array)
    }
}

fun siftDown(startIdx: Int, endIdx: Int, array: MutableList<Int>) {
    var runningStartIdx = startIdx
    var firstChildIdx = runningStartIdx * 2 + 1
    while (firstChildIdx <= endIdx) {
        val secondChildIdx = runningStartIdx * 2 + 2

        val idxToSwap = if (secondChildIdx <= endIdx && array[secondChildIdx] > array[firstChildIdx]) {
            secondChildIdx
        } else firstChildIdx

        if (array[idxToSwap] > array[runningStartIdx]) {
            swap(idxToSwap, runningStartIdx, array)
            runningStartIdx = idxToSwap
            firstChildIdx = runningStartIdx * 2 + 1
        } else {
            return
        }
    }
}