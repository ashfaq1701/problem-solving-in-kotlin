package ctci.maxPointsOnALine.solution1

import kotlin.math.abs
import kotlin.math.max

class Solution {
    fun maxPoints(points: Array<IntArray>): Int {
        var maxPoints = 1

        for (i in 0 .. points.lastIndex) {
            // Create a brand new map to store slopes only for
            // lines which include this P0.
            // In this way if we find multiple pairs
            // having the same slope we know that all those points
            // lie on the same line because they all
            // pass through a common point p0.
            val slopes = mutableMapOf<String, Int>()
            val p1 = points[i]

            for (j in 0 .. points.lastIndex) {

                if (i == j) continue

                val p2 = points[j]
                val (rise, run) = getSlope(p1, p2)
                val slopeKey = getSlopeKey(rise, run)

                if (slopeKey !in slopes) {
                    slopes[slopeKey] = 1
                }
                slopes[slopeKey] = slopes[slopeKey]!! + 1

                maxPoints = max(maxPoints, slopes[slopeKey]!!)
            }
        }

        return maxPoints
    }

    fun getSlope(p1: IntArray, p2: IntArray): Pair<Int, Int> {
        val (p1X, p1Y) = p1
        val (p2X, p2Y) = p2

        var rise = p1Y - p2Y
        var run  = p1X - p2X

        val gcd = findGcd(abs(rise), abs(run))

        rise /= gcd
        run  /= gcd

        return if (run < 0) -rise to -run else rise to run
    }

    fun getSlopeKey(rise: Int, run: Int): String {
        return "$rise|$run"
    }

    fun findGcd(a: Int, b: Int): Int {
        if (a == 0) return b

        if (b == 0) return a

        return findGcd(b, a % b)
    }
}