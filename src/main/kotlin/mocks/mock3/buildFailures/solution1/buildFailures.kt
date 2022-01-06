package mocks.mock3.buildFailures.solution1

import kotlin.math.max

fun buildFailures(buildRuns: List<List<Boolean>>): Int {

    val greenPercentages = countGreenPercentages(buildRuns)

    var longestDecreasingCount = 0
    var runningDecreasingCount = 1

    for (i in 1 .. buildRuns.lastIndex) {
        if (greenPercentages[i] < greenPercentages[i - 1]) {
            runningDecreasingCount += 1
            longestDecreasingCount = max(longestDecreasingCount, runningDecreasingCount)
        } else {
            runningDecreasingCount = 1
        }
    }

    return if (longestDecreasingCount <= 1) -1 else longestDecreasingCount
}

fun countGreenPercentages(buildRuns: List<List<Boolean>>): List<Double> {
    return buildRuns.map { buildRun ->
        val firstFalseIdx = getFirstFalseIdx(buildRun)
        firstFalseIdx.toDouble() / buildRun.size
    }
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