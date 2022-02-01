package ctci.divideTwoIntegers.solution2

const val HALF_INT_MIN = -1073741824

class Solution {

    // Repeated exponential searches
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

        while (transformedDividend <= transformedDivisor) {
            var powerOfTwo = -1
            var runningValue = transformedDivisor

            while (runningValue >= HALF_INT_MIN && runningValue + runningValue >= transformedDividend) {
                runningValue += runningValue
                powerOfTwo += powerOfTwo
            }

            transformedDividend -= runningValue
            quotient += powerOfTwo
        }

        return if (negatives != 1) -quotient else quotient
    }
}