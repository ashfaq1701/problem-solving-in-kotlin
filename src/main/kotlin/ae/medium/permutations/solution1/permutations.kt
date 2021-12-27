package ae.medium.permutations.solution1

fun getPermutations(array: List<Int>): List<List<Int>> {
    val permutations = mutableListOf<List<Int>>()

    if (array.isEmpty()) return permutations

    permutationsHelper(0, array.toMutableList(), permutations)
    return permutations
}

fun permutationsHelper(i: Int, array: MutableList<Int>, permutations: MutableList<List<Int>>) {
    if (i == array.size) {
        permutations.add(array.toList())
    }

    for (j in i .. array.lastIndex) {
        swap (array, i, j)
        permutationsHelper(i + 1, array, permutations)
        swap(array, j, i)
    }
}

fun swap(array: MutableList<Int>, i: Int, j: Int) {
    array[i] = array[j].also {
        array[j] = array[i]
    }
}