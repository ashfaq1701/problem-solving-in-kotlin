package mocks.mock2.spinRings.solution1

fun spinRings(array: List<MutableList<Int>>) {
    spinRingsHelper(array, 0, array.lastIndex, 0, array.lastIndex)
}

fun spinRingsHelper(array: List<MutableList<Int>>, sR: Int, eR: Int, sC: Int, eC: Int) {
    if (sR >= eR || sC >= eC) return

    var currentRow = sR
    var currentCol = sC
    var prevValue = array[sR + 1][sC]

    while (currentCol <= eC) {
        array[currentRow][currentCol] = prevValue.also {
            prevValue = array[currentRow][currentCol]
        }

        currentCol += 1
    }

    currentRow = sC + 1
    currentCol = eC
    while (currentRow <= eR) {
        array[currentRow][currentCol] = prevValue.also {
            prevValue = array[currentRow][currentCol]
        }

        currentRow += 1
    }

    currentRow = eR
    currentCol = eC - 1
    while (currentCol >= sC) {
        array[currentRow][currentCol] = prevValue.also {
            prevValue = array[currentRow][currentCol]
        }

        currentCol -= 1
    }

    currentRow = eR - 1
    currentCol = sC
    while (currentRow > sR) {
        array[currentRow][currentCol] = prevValue.also {
            prevValue = array[currentRow][currentCol]
        }

        currentRow -= 1
    }

    spinRingsHelper(array, sR + 1, eR - 1, sC + 1, eC - 1)
}
