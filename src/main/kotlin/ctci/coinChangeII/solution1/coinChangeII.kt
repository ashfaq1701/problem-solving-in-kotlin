package ctci.coinChangeII.solution1

class Solution {
    fun change(amount: Int, coins: IntArray): Int {
        val ways = MutableList(amount + 1) { 0 }
        ways[0] = 1

        for (coin in coins) {
            for (amount in coin .. amount) {
                ways[amount] += ways[amount - coin]
            }
        }

        return ways[amount]
    }
}