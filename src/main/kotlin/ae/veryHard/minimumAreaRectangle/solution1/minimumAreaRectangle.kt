package ae.veryHard.minimumAreaRectangle.solution1

import kotlin.math.*

fun minimumAreaRectangle(points: List<List<Int>>): Int {
    val pointsSet = getPointsSet(points)

    var minArea = Integer.MAX_VALUE

    for (i in 0 .. points.lastIndex) {

        val p1 = points[i]
        val (p1X, p1Y) = p1

        for (j in i + 1 .. points.lastIndex) {

            val p2 = points[j]
            val (p2X, p2Y) = p2

            val areOnSameAxes = p1X == p2X || p1Y == p2Y

            if (areOnSameAxes) continue

            val diagonalKey1 = convertPointToString(listOf(p1X, p2Y))
            val diagonalKey2 = convertPointToString(listOf(p2X, p1Y))

            if (pointsSet.contains(diagonalKey1) && pointsSet.contains(diagonalKey2)) {

                minArea = min(minArea, calculateArea(p1, p2))
            }
        }
    }

    return if (minArea == Integer.MAX_VALUE) 0 else minArea
}

fun getPointsSet(points: List<List<Int>>): Set<String> {
    val pointsSet = mutableSetOf<String>()

    points.forEach { point ->
        pointsSet.add(convertPointToString(point))
    }

    return pointsSet
}

fun convertPointToString(point: List<Int>): String {
    return point.joinToString("-")
}

fun calculateArea(point1: List<Int>, point2: List<Int>): Int {
    return abs(point1[0] - point2[0]) * abs(point1[1] - point2[1])
}
