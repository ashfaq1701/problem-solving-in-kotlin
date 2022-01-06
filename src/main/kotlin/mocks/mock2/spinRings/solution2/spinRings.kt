package mocks.mock2.spinRings.solution2

fun spinRings(array: List<MutableList<Int>>) {
    spinRingsHelper(array, 0, array.lastIndex, 0, array.lastIndex)
}

fun spinRingsHelper(array: List<MutableList<Int>>, sR: Int, eR: Int, sC: Int, eC: Int) {
    if (sR >= eR || sC >= eC) return

    val originalTopRightValue = array[sR][eC]

    for (col in eC downTo sC + 1) {
        array[sR][col] = array[sR][col - 1]
    }

    for (row in sR until eR) {
        array[row][sC] = array[row + 1][sC]
    }

    for (col in sC until eC) {
        array[eR][col] = array[eR][col + 1]
    }

    for (row in eR downTo sR + 1) {
        array[row][eC] = array[row - 1][eC]
    }

    array[sR + 1][eC] = originalTopRightValue

    spinRingsHelper(array, sR + 1, eR - 1, sC + 1, eC - 1)
}
