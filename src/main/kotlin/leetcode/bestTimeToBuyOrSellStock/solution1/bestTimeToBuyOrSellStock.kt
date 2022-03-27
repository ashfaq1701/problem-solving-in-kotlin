package leetcode.bestTimeToBuyOrSellStock.solution1

import kotlin.math.max

class Solution {
    fun maxProfit(prices: IntArray): Int {
        var minTillNow = Int.MAX_VALUE
        var maxProfit = 0

        for (price in prices) {
            if (price < minTillNow) {
                minTillNow = price
            } else {
                maxProfit = max(price - minTillNow, maxProfit)
            }
        }

        return maxProfit
    }
}