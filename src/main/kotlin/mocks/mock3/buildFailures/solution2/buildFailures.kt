package mocks.mock3.buildFailures.solution2

import kotlin.math.max

fun buildFailures(buildRuns: List<List<Boolean>>): Int {

    var lastGreenPercentage = Double.MAX_VALUE
    var currentDecreasingCount = 0
    var longestDecreasingCount = 0

    for (buildRun in buildRuns) {
        val currentGreenPercentage = calculateGreenPercentage(buildRun)

        if (currentGreenPercentage < lastGreenPercentage) {
            currentDecreasingCount += 1
            longestDecreasingCount = max(currentDecreasingCount, longestDecreasingCount)
        } else {
            currentDecreasingCount = 1
        }

        lastGreenPercentage = currentGreenPercentage
    }

    return if (longestDecreasingCount <= 1) -1 else longestDecreasingCount
}

fun calculateGreenPercentage(buildRun: List<Boolean>): Double {
    val firstFalseIdx = getFirstFalseIdx(buildRun)
    return firstFalseIdx.toDouble() / buildRun.size
}

fun getFirstFalseIdx(buildRun: List<Boolean>): Int {
    var start = 0
    var end = buildRun.size

    while (start <= end) {
        val mid = (start + end) / 2

        if (buildRun[mid]) {
            start = mid + 1
        } else {
            if (mid == start || buildRun[mid - 1]) {
                return mid
            } else {
                end = mid - 1
            }
        }
    }

    return -1
}