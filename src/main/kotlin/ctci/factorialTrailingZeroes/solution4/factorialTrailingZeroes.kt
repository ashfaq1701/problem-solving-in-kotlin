package ctci.factorialTrailingZeroes.solution4

class Solution {
    fun trailingZeroes(n: Int): Int {
        var zeroes = 0
        var currentMultiple = 5

        while (currentMultiple <= n) {
            zeroes += (n / currentMultiple)
            currentMultiple *= 5
        }

        return zeroes
    }
}