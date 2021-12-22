package ae.veryHard.maxProfitWithKTransactions.solution1

import kotlin.math.max

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
