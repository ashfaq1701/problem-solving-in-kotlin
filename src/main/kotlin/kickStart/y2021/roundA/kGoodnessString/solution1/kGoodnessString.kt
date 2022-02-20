package kickStart.y2021.roundA.kGoodnessString.solution1

import kotlin.math.abs

fun parseInputAndCalculateResult() {
    val numTests = readLine()!!.toInt()

    for (i in 1 .. numTests) {

        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val testString = readLine()!!

        val goodnessScore = calculateGoodnessScore(testString)
        val operationsRequired = abs(k - goodnessScore)

        println("Case #$i: $operationsRequired")
    }
}

fun calculateGoodnessScore(string: String): Int {
    var left = 0
    var right = string.lastIndex

    var goodnessScore = 0

    while (left < right) {
        if (string[left] != string[right]) {
            goodnessScore += 1
        }

        left += 1
        right -= 1
    }

    return goodnessScore
}

fun main() {
    parseInputAndCalculateResult()
}

