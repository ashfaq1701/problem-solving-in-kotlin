package leetcode.powXN.solution1

class Solution {
    fun myPow(x: Double, n: Int): Double {
        if (n < 0) {
            return fastPower(1.0 / x, -n)
        }

        return fastPower(x, n)
    }

    fun fastPower(x: Double, n: Int): Double {
        if (n == 0) return 1.0

        val halfPower = fastPower(x, n / 2)

        return if (n % 2 == 0) {
            halfPower * halfPower
        } else {
            halfPower * halfPower * x
        }
    }
}