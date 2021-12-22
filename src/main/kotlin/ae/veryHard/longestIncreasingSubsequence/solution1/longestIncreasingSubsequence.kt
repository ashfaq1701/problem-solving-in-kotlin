package ae.veryHard.longestIncreasingSubsequence.solution1

fun longestIncreasingSubsequence(array: List<Int>): List<Int> {
    val lengths = MutableList<Int>(array.size) { 1 }
    val sequences = MutableList<Int>(array.size) { -1 }

    var longestIdx = 0

    for (i in 0 .. array.lastIndex) {

        for (j in 0 until i) {

            if (array[j] < array[i] && lengths[j] + 1 > lengths[i]) {
                lengths[i] = lengths[j] + 1
                sequences[i] = j
            }
        }

        if (lengths[i] > lengths[longestIdx]) {
            longestIdx = i
        }
    }

    return buildSequence(sequences, array, longestIdx)
}

fun buildSequence(sequences: List<Int>, array: List<Int>, longestIdx: Int): List<Int> {
    var currentIdx = longestIdx
    val result = mutableListOf<Int>()

    while (currentIdx != -1) {
        result.add(array[currentIdx])
        currentIdx = sequences[currentIdx]
    }

    // Or result.reverse() and return result
    return result.reversed()
}
