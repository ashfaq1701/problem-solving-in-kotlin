package ctci.sumOfTwoIntegers.solution1

import kotlin.math.abs

class Solution {
    fun getSum(a: Int, b: Int): Int {
        var x = abs(a)
        var y = abs(b)

        if (y > x) return getSum(b, a)

        // A has larger absolute value.
        // So result will have a's sign.
        // So a determines the sign.
        val sign = if (a > 0) 1 else -1

        // If both number has same sign
        if (a * b > 0) {

            while (y > 0) {
                val answer = x xor y
                val carry = (x and y) shl 1
                x = answer
                y = carry
            }
            // If they have opposite signs
        } else {

            while (y > 0) {
                val answer = x xor y
                val carry = (x.inv() and y) shl 1
                x = answer
                y = carry
            }
        }

        return x * sign
    }
}