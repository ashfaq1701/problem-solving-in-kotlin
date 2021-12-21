package ae.veryHard.lineThroughPoints.solution1

import kotlin.math.max

fun lineThroughPoints(points: List<List<Int>>): Int {
    var maxPointsThroughLines = 1

    points.forEachIndexed { i, p0 ->

        // Create a brand new map to store slopes only for
        // lines which include this P0.
        // In this way if we find multiple pairs
        // having the same slope we know that all those points
        // lie on the same line because they all
        // pass through a common point p0.
        val slopes = mutableMapOf<String, Int>()

        for (j in i + 1 .. points.lastIndex) {

            val p1 = points[j]

            val slope = getSlopeBetweenPoints(p0, p1)
            val (rise, run) = slope
            val slopeKey = getSlopeKey(rise, run)

            if (!slopes.containsKey(slopeKey)) {
                slopes[slopeKey] = 1
            }
            slopes[slopeKey] = slopes[slopeKey]!! + 1
        }

        getMaxValueFromMap(slopes)?.let { maxValue ->
            maxPointsThroughLines = max(maxPointsThroughLines, maxValue)
        }
    }

    return maxPointsThroughLines
}

fun getMaxValueFromMap(slopes: Map<String, Int>) =
    slopes.values.maxOrNull()

fun getSlopeBetweenPoints(p0: List<Int>, p1: List<Int>): List<Int> {
    val (p0X, p0Y) = p0
    val (p1X, p1Y) = p1

    var rise = p1Y - p0Y
    var run = p1X - p0X
    val gcd = findGcd(rise, run)

    rise /= gcd
    run /= gcd

    if (run < 0) {
        return listOf(-rise, -run)
    }

    return listOf(rise, run)
}

fun findGcd(a: Int, b: Int): Int {
    if (a == 0) return b
    if (b == 0) return a
    return findGcd(b, a % b)
}

fun getSlopeKey(rise: Int, run: Int) = "$rise|$run"
