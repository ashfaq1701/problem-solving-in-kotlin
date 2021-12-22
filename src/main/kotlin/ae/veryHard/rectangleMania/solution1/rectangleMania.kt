package ae.veryHard.rectangleMania.solution1

fun rectangleMania(coords: List<List<Int>>): Int {
    val coordsSet = getCoordsSet(coords)
    return getRectangleCount(coords, coordsSet)
}

fun getCoordsSet(coords: List<List<Int>>): Set<String> {
    return coords.map {
        coordsToString(it)
    }.toSet()
}

fun coordsToString(coord: List<Int>) = "${coord[0]}|${coord[1]}"

fun getRectangleCount(coords: List<List<Int>>, coordsSet: Set<String>): Int {
    var rectangleCount = 0

    for (point1 in coords) {

        for (point2 in coords) {

            if (!isOnUpperRightDiagonal(point1, point2)) {
                continue
            }

            val (x1, y1) = point1
            val (x2, y2) = point2

            val topLeftPointKey = coordsToString(listOf(x1, y2))
            val bottomRightPointKey = coordsToString(listOf(x2, y1))

            if (coordsSet.contains(topLeftPointKey) && coordsSet.contains(bottomRightPointKey)) {
                rectangleCount++
            }
        }
    }

    return rectangleCount
}

fun isOnUpperRightDiagonal(point1: List<Int>, point2: List<Int>): Boolean {
    val (x1, y1) = point1
    val (x2, y2) = point2

    return x2 > x1 && y2 > y1
}