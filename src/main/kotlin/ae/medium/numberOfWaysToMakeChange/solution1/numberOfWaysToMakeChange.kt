package ae.medium.numberOfWaysToMakeChange.solution1

fun numberOfWaysToMakeChange(n: Int, denoms: List<Int>): Int {
    val ways = MutableList(n + 1) { 0 }
    ways[0] = 1

    denoms.forEach { denom ->
        for (amount in denom .. n) {
            ways[amount] += ways[amount - denom]
        }
    }

    return ways[n]
}