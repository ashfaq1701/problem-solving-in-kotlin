package ae.veryHard.maxProfitWithKTransactions.solution1

import kotlin.math.max

/**
 * prices ->   [ 5, 11,  3, 50, 60, 90 ]
 *           0   0   0   0   0   0   0
 * profit -> 1   0   6   6  47  57  87
 *           2   0   6   6  53  63
 *
 * profit[t][d] = max { 1) profit[t][d - 1]
 *                      2) prices[d] + max(-prices[x] + profit[t - 1][x])
 *                                     0 <= x < d
 * ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *
 * Explanation:
 *
 *                           Profit of 1 day before with same (t) number of transactions
 *                       1)                     profit[t][d - 1]
 *                                      Price of current day (income)                     Price of xth day (bought at this price, expense)     Profit till xth day with one less (t - 1) number of transactions
 * profit[t][d] = max {  2)                     prices[d]            +             max(                    -prices[x]                       +                      profit[t - 1][x]                              )
 *                                                         This will be calculated from 0th day till the last day (d - 1)
 *                                                                                 0 <= x < d
 */

fun maxProfitWithKTransactions(prices: List<Int>, k: Int): Int {
    if (prices.isEmpty()) return 0

    val profits = MutableList(k + 1) {
        MutableList(prices.size) { 0 }
    }

    for (t in 1 .. k) {

        var maxSoFar = Integer.MIN_VALUE

        for (d in 1 .. prices.lastIndex) {

            maxSoFar = max(maxSoFar, profits[t - 1][d - 1] - prices[d - 1])
            profits[t][d] = max(profits[t][d - 1], prices[d] + maxSoFar)
        }
    }

    return profits[k][prices.lastIndex]
}
