package ae.veryHard.rectangleMania.solution2

const val UP = "up"
const val RIGHT = "right"
const val DOWN = "down"

fun rectangleMania(coords: List<List<Int>>): Int {
    val coordsMap = getCoordsMap(coords)
    return getRectangleCount(coords, coordsMap)
}

fun getCoordsMap(coords: List<List<Int>>): Map<Char, Map<Int, List<List<Int>>>> {
    val coordsMap = mutableMapOf(
        'x' to mutableMapOf<Int, MutableList<List<Int>>>(),
        'y' to mutableMapOf<Int, MutableList<List<Int>>>()
    )

    for (coord in coords) {
        val (x, y) = coord

        if (!coordsMap['x']!!.containsKey(x)) {
            coordsMap['x']!![x] = mutableListOf()
        }
        coordsMap['x']!![x]!!.add(coord)

        if (!coordsMap['y']!!.containsKey(y)) {
            coordsMap['y']!![y] = mutableListOf()
        }
        coordsMap['y']!![y]!!.add(coord)
    }

    return coordsMap.mapValues { (_, axesMap) ->
        axesMap.mapValues { (_, coordList) -> coordList.toList() }
    }
}

fun getRectangleCount(coords: List<List<Int>>, coordsMap: Map<Char, Map<Int, List<List<Int>>>>): Int {
    var rectangleCount = 0

    for (coord in coords) {
        rectangleCount += clockwiseCountRectangle(coords, coordsMap, coord, UP, coord[1])
    }

    return rectangleCount
}


fun clockwiseCountRectangle(coords: List<List<Int>>, coordsMap: Map<Char, Map<Int, List<List<Int>>>>, coord: List<Int>, direction: String, lowerLeftY: Int): Int {
    val (x, y) = coord

    return if (direction == DOWN) {
        for ((nextX, nextY) in coordsMap['x']!![x]!!) {
            if (nextY == lowerLeftY) {
                return 1
            }
        }

        0
    } else {
        var rectangleCount = 0

        if (direction == UP) {
            for (nextCoord in coordsMap['x']!![x]!!) {
                if (nextCoord[1] > y) {
                    rectangleCount += clockwiseCountRectangle(coords, coordsMap, nextCoord, RIGHT, lowerLeftY)
                }
            }
        } else if (direction == RIGHT) {
            for (nextCoord in coordsMap['y']!![y]!!) {
                if (nextCoord[0] > x) {
                    rectangleCount += clockwiseCountRectangle(coords, coordsMap, nextCoord, DOWN, lowerLeftY)
                }
            }
        }

        rectangleCount
    }
}