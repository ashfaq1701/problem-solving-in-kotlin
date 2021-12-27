package ae.medium.sunsetViews.solution1

import kotlin.math.max

fun sunsetViews(buildings: List<Int>, direction: String): List<Int> {
    val range = if (direction == "EAST") {
        buildings.lastIndex downTo 0
    } else {
        0 .. buildings.lastIndex
    }

    var runningMax = 0
    val buildingsWithSunsetView = mutableListOf<Int>()

    for (idx in range) {
        val currentHeight = buildings[idx]

        if (currentHeight > runningMax) {
            buildingsWithSunsetView.add(idx)
        }

        runningMax = max(runningMax, currentHeight)
    }

    if (direction == "EAST") {
        buildingsWithSunsetView.reverse()
    }

    return buildingsWithSunsetView
}
