package ctci.factorialTrailingZeroes.solution2

class Solution {
    fun trailingZeroes(n: Int): Int {
        var fives = 0

        for (i in 5 .. n step 5) {
            var currI = i

            while (currI % 5 == 0) {
                fives += 1
                currI /= 5
            }
        }

        return fives
    }
}