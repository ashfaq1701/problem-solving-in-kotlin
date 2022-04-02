package codejam.y2022.qualification.threeDPrinting.solution1

import kotlin.math.max
import kotlin.math.min

fun getColorCombinationsForTestCases() {
    val testCaseCount = readLine()!!.toInt()

    for (i in 1 .. testCaseCount) {
        val firstPrinterColors = readLine()!!.split(" ").map { it.toInt() }
        val secondPrinterColors = readLine()!!.split(" ").map { it.toInt() }
        val thirdPrinterColors = readLine()!!.split(" ").map { it.toInt() }

        print("Case #$i: ")
        val colorCombinations = getColorCombinations(firstPrinterColors, secondPrinterColors, thirdPrinterColors)
        if (colorCombinations == null) {
            println("IMPOSSIBLE")
        } else {
            println(colorCombinations.joinToString(" "))
        }
        println()
    }
}

fun getColorCombinations(firstColors: List<Int>, secondColors: List<Int>, thirdColors: List<Int>): List<Int>? {
    val (c1, m1, y1, k1) = firstColors
    val (c2, m2, y2, k2) = secondColors
    val (c3, m3, y3, k3) = thirdColors

    val minC = listOf(c1, c2, c3).minOrNull()!!
    val minM = listOf(m1, m2, m3).minOrNull()!!
    val minY = listOf(y1, y2, y3).minOrNull()!!
    val minK = listOf(k1, k2, k3).minOrNull()!!

    if (minC + minM + minY + minK < 1000000) {
        return null
    }

    var remainder = 1000000
    val colorValues = mutableListOf(0, 0, 0, 0)

    val minColors = listOf(minC to 0, minM to 1, minY to 2, minK to 3).sortedWith(Comparator { p1, p2 ->
        p1.first.compareTo(p2.first)
    })

    for (minColor in minColors) {
        val (color, idx) = minColor
        val colorToTake = min(remainder, color)
        colorValues[idx] = colorToTake
        remainder = max(remainder - colorToTake, 0)
    }

    return colorValues
}

fun main() {
    getColorCombinationsForTestCases()
}