package ae.medium.powerset.solution2

fun powerset(array: List<Int>): List<List<Int>> {
    return powersetHelper(array, 0)
}

fun powersetHelper(array: List<Int>, idx: Int): MutableList<List<Int>> {
    if (idx >= array.size) return mutableListOf(listOf())

    val item = array[idx]
    val subsets = powersetHelper(array, idx + 1)

    val size = subsets.size

    for (i in 0 until size) {
        val currentSubset = subsets[i]
        subsets.add(currentSubset.plus(item))
    }

    return subsets
}