package ae.hard.waterArea.solution1

import kotlin.math.max
import kotlin.math.min

fun waterArea(heights: List<Int>): Int {
    val maxes = MutableList(heights.size) { 0 }
    var area = 0

    var leftMax = 0
    for (i in 0 .. heights.lastIndex) {
        maxes[i] = leftMax
        val height = heights[i]
        leftMax = max(leftMax, height)
    }

    var rightMax = 0
    for (i in heights.lastIndex downTo 0) {
        val minHeight = min(maxes[i], rightMax)
        val height = heights[i]

        if (height < minHeight) {
            maxes[i] = minHeight - height
        } else {
            maxes[i] = 0
        }

        area += maxes[i]
        rightMax = max(rightMax, height)
    }

    return area
}
