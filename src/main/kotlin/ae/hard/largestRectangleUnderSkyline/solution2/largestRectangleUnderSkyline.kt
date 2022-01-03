package ae.hard.largestRectangleUnderSkyline.solution2

import kotlin.math.max

fun largestRectangleUnderSkyline(buildings: List<Int>): Int {
    var maxArea = 0
    val pillarIdxs = mutableListOf<Int>()

    // To clear out stack at the end of the algorithm
    val extendedBuildings = buildings.plus(0)

    for (i in extendedBuildings.indices) {
        val height = extendedBuildings[i]

        // Remove elements from top of the stack as long as
        // Top element from stack has a greater than or equal to the height
        // of the current element pointed with i
        while (pillarIdxs.isNotEmpty() && extendedBuildings[pillarIdxs.last()] >= height) {
            val pillarHeight = extendedBuildings[pillarIdxs.removeAt(pillarIdxs.lastIndex)]

            // i is the right bound
            // pillarIdxs.back() us the left bound
            // Lower element on stack always should have smaller value
            // If not then they must have been removed already
            // both right and left bounds are indices which we can't include
            // So we do rightBound - leftBound - 1
            val width = if (pillarIdxs.isEmpty()) {
                i
            } else {
                i - pillarIdxs.last() - 1
            }

            maxArea = max(maxArea, pillarHeight * width)
        }

        pillarIdxs.add(i)
    }

    return maxArea
}
