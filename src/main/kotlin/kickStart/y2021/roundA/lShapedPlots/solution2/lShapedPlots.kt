package kickStart.y2021.roundA.lShapedPlots.solution2

import kotlin.math.min

fun parseInputAndCalculateResult() {
    val numTests = readLine()!!.toInt()

    for (i in 1..numTests) {
        val (numRows, numCols) = readLine()!!.split(" ").map { it.toInt() }

        val matrix = (0 until numRows).map {
            readLine()!!.split(" ").map { it.toInt() }
        }

        val countLShapedPlots = getLShapedPlotCounts(matrix)
        println("Case #$i: $countLShapedPlots")
    }
}

fun getLShapedPlotCounts(matrix: List<List<Int>>): Int {
    val rows = matrix.size
    val cols = matrix[0].size
    val leftCounts = matrix.map { it.toMutableList() }
    val topCounts = matrix.map { it.toMutableList() }
    val rightCounts = matrix.map { it.toMutableList() }
    val bottomCounts = matrix.map { it.toMutableList() }

    populateTopCounts(matrix, topCounts)
    populateLeftCounts(matrix, leftCounts)
    populateRightCounts(matrix, rightCounts)
    populateBottomCounts(matrix, bottomCounts)

    var totalLShapesCount = 0

    for (r in 0 until rows) {
        for (c in 0 until cols) {
            if (matrix[r][c] == 0) continue

            val lShapesMeetingHere = getLShapesCount(topCounts[r][c], leftCounts[r][c]) +
                    getLShapesCount(topCounts[r][c], rightCounts[r][c]) +
                    getLShapesCount(bottomCounts[r][c], leftCounts[r][c]) +
                    getLShapesCount(bottomCounts[r][c], rightCounts[r][c])
            totalLShapesCount += lShapesMeetingHere
        }
    }

    return totalLShapesCount
}

fun populateTopCounts(matrix: List<List<Int>>, topCounts: List<MutableList<Int>>) {
    val rows = matrix.size
    val cols = matrix[0].size

    for (r in 1 until rows) {
        for (c in 0 until cols) {
            if (matrix[r][c] == 0) continue
            topCounts[r][c] = topCounts[r - 1][c] + 1
        }
    }
}

fun populateLeftCounts(matrix: List<List<Int>>, leftCounts: List<MutableList<Int>>) {
    val rows = matrix.size
    val cols = matrix[0].size

    for (r in 0 until rows) {
        for (c in 1 until cols) {
            if (matrix[r][c] == 0) continue
            leftCounts[r][c] = leftCounts[r][c - 1] + 1
        }
    }
}

fun populateRightCounts(matrix: List<List<Int>>, rightCounts: List<MutableList<Int>>) {
    val rows = matrix.size
    val cols = matrix[0].size

    for (r in 0 until rows) {
        for (c in cols - 2 downTo 0) {
            if (matrix[r][c] == 0) continue
            rightCounts[r][c] = rightCounts[r][c + 1] + 1
        }
    }
}

fun populateBottomCounts(matrix: List<List<Int>>, bottomCounts: List<MutableList<Int>>) {
    val rows = matrix.size
    val cols = matrix[0].size

    for (r in rows - 2 downTo 0) {
        for (c in 0 until  cols) {
            if (matrix[r][c] == 0) continue
            bottomCounts[r][c] = bottomCounts[r + 1][c] + 1
        }
    }
}

fun getLShapesCount(x: Int, y: Int) : Int {
    if (x == 1 || y == 1) return 0
    return min(x / 2, y) + min(y / 2, x) - 2
}

fun main() {
    parseInputAndCalculateResult()
}
