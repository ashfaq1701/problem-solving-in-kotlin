package ae.hard.largestRectangleUnderSkyline.solution1

import kotlin.math.max

fun largestRectangleUnderSkyline(buildings: List<Int>): Int {
    var maxArea = 0

    for (i in buildings.indices) {
        val currentPillarHeight = buildings[i]

        var furthestLeftIdx = i
        while (furthestLeftIdx > 0 && buildings[furthestLeftIdx - 1] >= currentPillarHeight) {
            furthestLeftIdx -= 1
        }

        var furthestRightIdx = i
        while (furthestRightIdx < buildings.lastIndex && buildings[furthestRightIdx + 1] >= currentPillarHeight) {
            furthestRightIdx += 1
        }

        val currentArea = (furthestRightIdx - furthestLeftIdx + 1) * currentPillarHeight
        maxArea = max(maxArea, currentArea)
    }

    return maxArea
}
