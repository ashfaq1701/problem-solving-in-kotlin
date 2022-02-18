package kickStart.y2022.practiceSession1.hIndex

import java.util.*

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
    var runningHIndex = 1
    val heap = PriorityQueue<Int>()

    return citations.map { citation ->
        heap.add(citation)

        while (heap.isNotEmpty() && heap.peek() <= runningHIndex) {
            heap.poll()
        }

        if (heap.size > runningHIndex) {
            runningHIndex = heap.size
        }

        runningHIndex
    }
}