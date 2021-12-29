package ae.hard.maxSumIncreasingSubsequence.solution1

fun maxSumIncreasingSubsequence(array: List<Int>): Pair<Int, List<Int>> {
    val sequences = MutableList(array.size) { -1 }
    val sums = array.toMutableList()

    var maxSumIdx = 0

    for (i in 0 .. array.lastIndex) {

        val currentNum = array[i]

        for (j in 0 until i) {
            val otherNum = array[j]

            // If we add currentNum with whatever sum is available at index j
            // and if it is more what whatever sum is available at index i
            // Then at index i store sum available at index j plus currentNum
            if (currentNum > otherNum && sums[j] + currentNum >= sums[i]) {
                sums[i] = sums[j] + currentNum
                sequences[i] = j
            }
        }

        if (sums[i] > sums[maxSumIdx]) {
            maxSumIdx = i
        }
    }

    return Pair(sums[maxSumIdx], buildSequences(array, sequences, maxSumIdx))
}

fun buildSequences(array: List<Int>, sequences: List<Int>, currentIdx: Int): List<Int> {
    val sequence = mutableListOf<Int>()
    var currentIdxMutable = currentIdx

    while (currentIdxMutable != -1) {
        sequence.add(array[currentIdxMutable])
        currentIdxMutable = sequences[currentIdxMutable]
    }

    sequence.reverse()

    return sequence
}
