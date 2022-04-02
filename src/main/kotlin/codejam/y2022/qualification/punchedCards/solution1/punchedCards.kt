package codejam.y2022.qualification.punchedCards.solution1

fun punchedCards() {
    val testCaseCount = readLine()!!.toInt()

    for (i in 1 .. testCaseCount) {
        val (rows, cols) = readLine()!!.split(" ").map { it.toInt() }
        val cardDrawing = getPunchCardDrawing(rows, cols)

        println("Case #$i:")
        cardDrawing.forEach {
            println(it)
        }
    }
}

fun getPunchCardDrawing(rows: Int, cols: Int): List<String> {
    val numRows = 2 * rows + 1
    val numCols = 2 * cols + 1

    val cardDrawing = MutableList(numRows) { StringBuilder() }

    for (r in 0 until numRows) {
        val currentSB = cardDrawing[r]

        for (c in 0 until numCols) {
            if (r % 2 == 0) {
                if (r == 0 && c < 2) {
                    currentSB.append('.')
                    continue
                }

                if (c % 2 == 0) {
                    currentSB.append('+')
                } else {
                    currentSB.append('-')
                }
            } else {
                if (r == 1 && c < 2) {
                    currentSB.append('.')
                    continue
                }

                if (c % 2 == 0) {
                    currentSB.append('|')
                } else {
                    currentSB.append('.')
                }
            }
        }
    }

    return cardDrawing.map { it.toString() }
}

fun main() {
    punchedCards()
}