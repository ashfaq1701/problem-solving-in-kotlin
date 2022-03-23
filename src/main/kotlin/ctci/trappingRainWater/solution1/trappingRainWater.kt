package ctci.trappingRainWater.solution1

import kotlin.math.max
import kotlin.math.min

class Solution {
    fun trap(height: IntArray): Int {
        val leftMaxes = MutableList(height.size) { 0 }
        var runningLeftMax = 0
        for (i in height.indices) {
            leftMaxes[i] = runningLeftMax
            runningLeftMax = max(runningLeftMax, height[i])
        }

        var rightMaxes = MutableList(height.size) { 0 }
        var runningRightMax = 0
        for (i in height.indices.reversed()) {
            rightMaxes[i] = runningRightMax
            runningRightMax = max(runningRightMax, height[i])
        }

        var areaOfWater = 0
        for (i in height.indices) {
            val minSide = min(leftMaxes[i], rightMaxes[i])

            if (minSide > height[i]) {
                areaOfWater += minSide - height[i]
            }
        }

        return areaOfWater
    }
}