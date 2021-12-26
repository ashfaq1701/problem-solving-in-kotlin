package ae.medium.moveElementToEnd.solution2

fun moveElementToEnd(array: MutableList<Int>, toMove: Int): List<Int> {
    var i = 0
    var j = array.lastIndex

    while (i < j) {
        while (i < j && array[j] == toMove) {
            j -= 1
        }

        if (array[i] == toMove) {
            swap(i, j, array)
        }

        i += 1
    }

    return array
}

fun swap(i: Int, j: Int, array: MutableList<Int>) {
    array[i] = array[j].also {
        array[j] = array[i]
    }
}
