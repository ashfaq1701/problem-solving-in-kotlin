package ctci.factorialTrailingZeroes.solution1

import java.math.BigInteger

class Solution {
    fun trailingZeroes(n: Int): Int {
        var nFactorial = BigInteger.ONE

        for (i in 2 .. n) {
            nFactorial *= BigInteger.valueOf(i.toLong())
        }

        var zeroesCount = 0

        while (nFactorial % BigInteger.TEN == BigInteger.ZERO) {
            zeroesCount += 1
            nFactorial /= BigInteger.TEN
        }

        return zeroesCount
    }
}