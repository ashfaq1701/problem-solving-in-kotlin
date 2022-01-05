package mocks.mock1.longestStreakOfAdjacentOnes.solution1

fun longestStreakOfAdjacentOnes(array: List<Int>): Int {
    var longestStreakLength = 0
    var longestStreakReplacedZeroIdx = -1

    var currentStreakLength = 0
    var lastReplacedZeroIdx = -1

    for (i in array.indices) {
        if (array[i] == 0) {
            currentStreakLength = i - lastReplacedZeroIdx
            lastReplacedZeroIdx = i
        } else {
            currentStreakLength += 1
        }

        if (currentStreakLength > longestStreakLength) {
            longestStreakLength = currentStreakLength
            longestStreakReplacedZeroIdx = lastReplacedZeroIdx
        }
    }

    return longestStreakReplacedZeroIdx
}
