package mocks.mock1.longestStreakOfAdjacentOnes.solution2

// NON OPTIMAL
fun longestStreakOfAdjacentOnes(array: List<Int>): Int {
    val leftOneCounts = MutableList<Int>(array.size) { 0 }

    var runningLeftOneCount = 0
    for (i in array.indices) {
        leftOneCounts[i] = runningLeftOneCount
        if (array[i] == 1) {
            runningLeftOneCount += 1
        } else {
            runningLeftOneCount = 0
        }
    }

    val rightOneCounts = MutableList<Int>(array.size) { 0 }

    var runningRightOneCount = 0
    for (i in array.lastIndex downTo 0) {
        rightOneCounts[i] = runningRightOneCount
        if (array[i] == 1) {
            runningRightOneCount += 1
        } else {
            runningRightOneCount = 0
        }
    }

    var maxConsecutiveOnes = 0
    var maxIdx = -1

    for (i in array.indices) {
        if (array[i] != 0) continue

        if (leftOneCounts[i] + rightOneCounts[i] + 1 > maxConsecutiveOnes) {
            maxConsecutiveOnes = leftOneCounts[i] + rightOneCounts[i] + 1
            maxIdx = i
        }
    }

    return maxIdx
}