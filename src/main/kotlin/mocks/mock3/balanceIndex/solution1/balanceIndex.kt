package mocks.mock3.balanceIndex.solution1

fun balanceIndex(array: List<Int>): Int {
    var leftSideSum = 0
    var rightSideSum = array.sum()

    for (i in array.indices) {
        rightSideSum -= array[i]

        if (leftSideSum == rightSideSum) {
            return i
        }

        leftSideSum += array[i]
    }

    return -1
}
