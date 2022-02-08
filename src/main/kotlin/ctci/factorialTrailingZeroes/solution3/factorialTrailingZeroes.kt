package ctci.factorialTrailingZeroes.solution3

class Solution {
    fun trailingZeroes(n: Int): Int {
        var fives = 0

        for (i in 5 .. n step 5) {
            var powerOf5 = 5

            while (i % powerOf5 == 0) {
                fives += 1
                powerOf5 *= 5
            }
        }

        return fives
    }
}