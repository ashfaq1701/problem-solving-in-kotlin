package ae.medium.powerset.solution1

fun powerset(array: List<Int>): List<List<Int>> {
    val subsets = mutableListOf<List<Int>>(listOf())

    for (num in array) {
        val size = subsets.size

        for (i in 0 until size) {
            val currentSubset = subsets[i]
            val newSubset = currentSubset.plus(num)
            subsets.add(newSubset)
        }
    }

    return subsets
}
