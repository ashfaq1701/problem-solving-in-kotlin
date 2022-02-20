package commonAlgo.rangeQuery

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

    fun query(topR: Int, leftCol: Int, bottomR: Int, rightC: Int): Int {
        var sum = auxMatrix[bottomR][rightC]

        if (topR > 0) {
            sum -= auxMatrix[topR - 1][rightC]
        }

        if (leftCol > 0) {
            sum -= auxMatrix[bottomR][leftCol - 1]
        }

        if (topR > 0 && leftCol > 1) {
            sum += auxMatrix[topR - 1][leftCol - 1]
        }

        return sum
    }
}

fun main() {
    val testArray = listOf(
        listOf(1, 2, 3, 4, 6),
        listOf(5, 3, 8, 1, 2),
        listOf(4, 6, 7, 5, 5),
        listOf(2, 4, 8, 9, 4)
    )

    val rangeQuery = RangeQuery(testArray)
    println(rangeQuery.query(1, 2, 3, 3))
}