package mocks.mock3.balanceIndex.solution2

fun balanceIndex(array: List<Int>): Int {
    val sums = MutableList(array.size) { 0 }
    var runningLeftSum = 0
    for (i in array.indices) {
        sums[i] = runningLeftSum
        runningLeftSum += array[i]
    }

    var runningRightSum = 0
    for (i in array.indices.reversed()) {
        if (sums[i] == runningRightSum) {
            return i
        }

        runningRightSum += array[i]
    }

    return -1
}
