package kickStart.y2021.roundA.lShapedPlots.solution2

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
    return 0
}

fun main() {
    parseInputAndCalculateResult()
}
