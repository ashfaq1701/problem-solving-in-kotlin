package codejam.y2022.qualification.d1000000.solution1

import kotlin.math.max

fun findMaxLensForTestCases() {
    val testCaseCount = readLine()!!.toInt()

    for (i in 1 .. testCaseCount) {
        val numDice = readLine()!!.toInt()
        val numFaces = readLine()!!.split(" ").map { it.toInt() }
        val maxLen = findMaxLen(numFaces)
        println("Case #$i: $maxLen")
    }
}

fun findMaxLen(faces: List<Int>): Int {
    val sortedFaces = faces.sorted()
    var maxLen = 1

    val dp = MutableList(sortedFaces.size) { 0 }

    dp[0] = 1
    if (sortedFaces.size > 1) {
        dp[1] = 2
        maxLen = 2
    }

    for (idx in 2 until dp.size) {
        val lastLen = max(dp[idx - 1], dp[idx - 2])
        dp[idx] = if (sortedFaces[idx] > lastLen) {
            lastLen + 1
        } else {
            4
        }
        maxLen = max(maxLen, dp[idx])
    }

    return maxLen
}

fun main() {
    findMaxLensForTestCases()
}