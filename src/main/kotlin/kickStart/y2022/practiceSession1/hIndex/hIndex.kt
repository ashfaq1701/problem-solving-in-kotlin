package kickStart.y2022.practiceSession1.hIndex

import kotlin.math.min

fun parseDataAndCalculateHIndex() {
    val numTests = readLine()!!.toInt()

    for (i in 1 .. numTests) {
        val numPapers = readLine()!!.toInt()
        val citations = readLine()!!.split(" ").map { it.toInt() }
        val hIndexes = calculateHIndexes(citations)
        println("Case #$i: ${hIndexes.joinToString(" ")}")
    }
}

fun calculateHIndexes(citations: List<Int>): List<Int> {
    val runningCounts = MutableList(citations.size + 1) { 0 }

    return citations.mapIndexed { idx, currentCitationCount ->
        val citationNumber = idx + 1

        val roundedCitationCount = min(currentCitationCount, citations.size)
        for (currentValue in roundedCitationCount downTo 1) {
            runningCounts[currentValue] += 1
        }

        var hIndex = 0
        for (currentValue in 1 .. citationNumber) {
            if (runningCounts[currentValue] >= currentValue) {
                hIndex = currentValue
            }
        }

        hIndex
    }
}