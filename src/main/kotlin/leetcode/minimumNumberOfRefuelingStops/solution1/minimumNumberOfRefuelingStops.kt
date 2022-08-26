package leetcode.minimumNumberOfRefuelingStops.solution1

import kotlin.math.max

class Solution {
    fun minRefuelStops(target: Int, startFuel: Int, stations: Array<IntArray>): Int {
        val n = stations.size
        val dp = MutableList<Long>(n + 1) { 0 }
        dp[0] = startFuel.toLong()

        for (i in 0 until n) {
            val currentStation = stations[i]
            val (currentLocation, currentFuel) = currentStation

            for (t in i downTo 0) {
                if (dp[t] >= currentLocation) {
                    dp[t + 1] = max(dp[t + 1], dp[t] + currentFuel)
                }
            }
        }

        for (i in 0 .. n) {
            if (dp[i] >= target) {
                return i
            }
        }

        return -1
    }
}