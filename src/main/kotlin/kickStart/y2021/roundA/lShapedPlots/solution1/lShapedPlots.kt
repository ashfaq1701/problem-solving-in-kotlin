package kickStart.y2021.roundA.lShapedPlots.solution1

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

    val rangeQuery = RangeQuery(matrix)

    var lShapeCount = 0

    for (r1 in 0 until rows) {
        for (c1 in 0 until cols) {

            for (r2 in r1 + 1 until rows) {
                for (c2 in c1 + 1 until cols) {

                    val colDistance = c2 - c1 + 1
                    val rowDistance = r2 - r1 + 1

                    val topRowQuery = rangeQuery.query(r1, c1, r1, c2)
                    val bottomRowQuery = rangeQuery.query(r2, c1, r2, c2)
                    val leftColQuery = rangeQuery.query(r1, c1, r2, c1)
                    val rightColQuery = rangeQuery.query(r1, c2, r2, c2)

                    var horizontalEdgesCount = 0
                    if (colDistance == topRowQuery) {
                        horizontalEdgesCount += 1
                    }
                    if (colDistance == bottomRowQuery) {
                        horizontalEdgesCount += 1
                    }

                    var verticalEdgesCount = 0
                    if (rowDistance == leftColQuery) {
                        verticalEdgesCount += 1
                    }
                    if (rowDistance == rightColQuery) {
                        verticalEdgesCount += 1
                    }

                    val containsHorizontalEdge = horizontalEdgesCount > 0
                    val containsVerticalEdge = verticalEdgesCount > 0
                    val lengthConstraintMet = colDistance == rowDistance * 2 || rowDistance == colDistance * 2

                    if (containsHorizontalEdge && containsVerticalEdge && lengthConstraintMet) {
                        lShapeCount += (horizontalEdgesCount * verticalEdgesCount)
                    }
                }
            }
        }
    }

    return lShapeCount
}

fun main() {
    parseInputAndCalculateResult()
}

class RangeQuery(private val matrix: List<List<Int>>) {
    val rows = matrix.size
    val cols = matrix[0].size

    private val auxMatrix: List<List<Int>> by lazy {
        val aux = MutableList(rows) { MutableList(cols) { 0 } }

        // Step 1: Copy the first row in the aux array
        for (c in 0 until cols) {
            aux[0][c] = matrix[0][c]
        }

        // Step 2: Prefix sum of the columns of aux array
        for (r in 1 until rows) {
            for (c in 0 until cols) {
                aux[r][c] = aux[r - 1][c] + matrix[r][c]
            }
        }

        // Step 3: Prefix sum of the rows of aux array
        for (r in 0  until rows) {
            for (c in 1 until cols) {
                aux[r][c] += aux[r][c - 1]
            }
        }

        aux.map { it.toList() }
    }

    fun query(topR: Int, leftC: Int, bottomR: Int, rightC: Int): Int {
        var sum = auxMatrix[bottomR][rightC]

        if (topR > 0) {
            sum -= auxMatrix[topR - 1][rightC]
        }

        if (leftC > 0) {
            sum -= auxMatrix[bottomR][leftC - 1]
        }

        if (topR > 0 && leftC > 0) {
            sum += auxMatrix[topR - 1][leftC - 1]
        }

        return sum
    }
}