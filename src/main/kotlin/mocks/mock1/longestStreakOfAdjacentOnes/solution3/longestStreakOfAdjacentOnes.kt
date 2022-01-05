package mocks.mock1.longestStreakOfAdjacentOnes.solution3

// NON OPTIMAL
fun longestStreakOfAdjacentOnes(array: List<Int>): Int {
    val oneCounts = MutableList<Int>(array.size) { 0 }

    var runningLeftOneCount = 0
    for (i in array.indices) {
        oneCounts[i] = runningLeftOneCount
        if (array[i] == 1) {
            runningLeftOneCount += 1
        } else {
            runningLeftOneCount = 0
        }
    }

    var runningRightOneCount = 0
    for (i in array.lastIndex downTo 0) {
        // If element is 1, then the addition of 1 is visibly legit
        // If element is 0, then we will replace it with 1 to get longest streak, so adding 1 is legit here too.
        oneCounts[i] += (runningRightOneCount + 1)
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

        if (oneCounts[i] > maxConsecutiveOnes) {
            maxConsecutiveOnes = oneCounts[i]
            maxIdx = i
        }
    }

    return maxIdx
}
