package ae.easy.validateSubsequence.solution1

fun isValidSubsequence(array: List<Int>, sequence: List<Int>): Boolean {
    var aIdx = 0
    var seqIdx = 0

    while (aIdx < array.size && seqIdx < sequence.size) {
        if (array[aIdx] == sequence[seqIdx]) {
            seqIdx += 1
        }

        aIdx += 1
    }

    return seqIdx == sequence.size
}
