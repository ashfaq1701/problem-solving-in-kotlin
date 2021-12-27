package ae.medium.sunsetViews.solution2

fun sunsetViews(buildings: List<Int>, direction: String): List<Int> {

    val range = if (direction == "EAST") {
        0 .. buildings.lastIndex
    } else {
        buildings.lastIndex downTo 0
    }

    val buildingsWithSunsetView = mutableListOf<Int>()

    for (idx in range) {
        val currentHeight = buildings[idx]

        // The items which current element can block we should pop them off
        while (buildingsWithSunsetView.isNotEmpty() && currentHeight >= buildings[buildingsWithSunsetView.last()]) {
            buildingsWithSunsetView.removeAt(buildingsWithSunsetView.lastIndex)
        }

        buildingsWithSunsetView.add(idx)
    }

    if (direction == "WEST") {
        buildingsWithSunsetView.reverse()
    }

    return buildingsWithSunsetView
}
