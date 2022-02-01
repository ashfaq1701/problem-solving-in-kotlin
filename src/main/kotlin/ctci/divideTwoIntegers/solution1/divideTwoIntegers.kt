package ctci.divideTwoIntegers.solution1

class Solution {

    // Repeated subtraction
    fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == Int.MIN_VALUE && divisor == -1) {
            return Int.MAX_VALUE
        }

        var negatives = 2
        var transformedDividend = if (dividend > 0) {
            negatives -= 1
            -dividend
        } else dividend

        val transformedDivisor = if (divisor > 0) {
            negatives -= 1
            -divisor
        } else divisor

        var quotient = 0

        while (transformedDividend - transformedDivisor <= 0) {
            transformedDividend -= transformedDivisor
            quotient += 1
        }

        return if (negatives == 1) -quotient else quotient
    }
}