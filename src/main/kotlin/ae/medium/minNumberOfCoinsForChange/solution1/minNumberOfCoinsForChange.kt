package ae.medium.minNumberOfCoinsForChange.solution1

import kotlin.math.min

fun minNumberOfCoinsForChange(n: Int, denoms: List<Int>): Int {
    val numCoins = MutableList(n + 1) { Integer.MAX_VALUE }
    numCoins[0] = 0

    denoms.forEach { denom ->
        for (amount in denom .. n) {
            val toCompare = if (numCoins[amount - denom] == Integer.MAX_VALUE) {
                numCoins[amount - denom]
            } else {
                1 + numCoins[amount - denom]
            }

            numCoins[amount] = min(numCoins[amount], toCompare)
        }
    }

    return if (numCoins[n] == Integer.MAX_VALUE) -1 else numCoins[n]
}
