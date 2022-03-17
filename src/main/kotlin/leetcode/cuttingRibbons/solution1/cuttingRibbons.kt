package leetcode.cuttingRibbons.solution1

import kotlin.math.max

class Solution {
    fun maxLength(ribbons: IntArray, k: Int): Int {
        var maxRibbonLength = Int.MIN_VALUE
        var sum = 0

        for (ribbon in ribbons) {
            maxRibbonLength = max(ribbon, maxRibbonLength)
            sum += ribbon
        }

        var left = 1
        var right = maxRibbonLength

        while (left <= right) {
            val mid = (left + right) / 2

            val count = getRibbonCountOfLength(ribbons, mid)

            if (count < k) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return right
    }

    fun getRibbonCountOfLength(ribbons: IntArray, ribbonLen: Int): Int {
        var count = 0

        for (ribbon in ribbons) {
            count += (ribbon / ribbonLen)
        }

        return count
    }
}