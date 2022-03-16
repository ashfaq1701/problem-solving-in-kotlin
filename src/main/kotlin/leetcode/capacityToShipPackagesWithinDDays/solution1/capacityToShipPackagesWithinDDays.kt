package leetcode.capacityToShipPackagesWithinDDays.solution1

import kotlin.math.max

class Solution {
    fun shipWithinDays(weights: IntArray, days: Int): Int {
        var maxWeight = Int.MIN_VALUE
        var totalWeight = 0

        for (weight in weights) {
            maxWeight = max(weight, maxWeight)
            totalWeight += weight
        }

        var left = maxWeight
        var right = totalWeight

        while (left < right) {
            val mid = (left + right) / 2

            // Capacity is too low, so it is taking more number of days.
            if (getCountOfDays(weights, mid) > days) {
                left = mid + 1
                // Capacity is okay, so it's taking correct or less number of days.
                // But even less capacity may or may not ship the items within d days.
                // So search for it.
            } else {
                right = mid
            }
        }

        return left
    }

    fun getCountOfDays(weights: IntArray, capacity: Int): Int {
        var days = 0
        var currentTotalWeight = 0

        for (weight in weights) {
            if (currentTotalWeight + weight > capacity) {
                days += 1
                currentTotalWeight = 0
            }

            currentTotalWeight += weight
        }

        days += 1
        return days
    }
}