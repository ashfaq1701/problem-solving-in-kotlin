package ae.medium.threeNumberSort.solution1

fun threeNumberSort(array: MutableList<Int>, order: List<Int>): List<Int> {
    val valueCounts = mutableListOf(0, 0, 0)
    val sortedArray = MutableList(array.size) { 0 }

    for (i in array) {
        val orderIndex = order.indexOf(i)
        valueCounts[orderIndex] += 1
    }

    var arrayIdx = 0

    for (i in 0 until 3) {
        val item = order[i]
        val count = valueCounts[i]

        for (j in 0 until count) {
            sortedArray[arrayIdx] = item
            arrayIdx += 1
        }
    }

    return sortedArray
}
