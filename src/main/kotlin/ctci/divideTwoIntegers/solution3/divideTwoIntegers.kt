package ctci.divideTwoIntegers.solution3

const val HALF_INT_MIN = -1073741824

class Solution {

    // Adding Powers of Two
    fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == Int.MIN_VALUE && divisor == -1) {
            return Int.MAX_VALUE
        }

        var negatives = 2
        var transformedDividend = if (dividend > 0) {
            negatives -= 1
            -dividend
        } else dividend

        var transformedDivisor = if (divisor > 0) {
            negatives -= 1
            -divisor
        } else divisor

        val powersOfTwo = mutableListOf<Int>()
        val doubles = mutableListOf<Int>()
        var powerOfTwo = -1

        while (transformedDividend <= transformedDivisor) {
            powersOfTwo.add(powerOfTwo)
            doubles.add(transformedDivisor)

            if (transformedDivisor < HALF_INT_MIN) {
                break
            }

            powerOfTwo += powerOfTwo
            transformedDivisor += transformedDivisor
        }

        var quotient = 0

        for (i in doubles.lastIndex downTo 0) {
            if (doubles[i] >= transformedDividend) {
                quotient += powersOfTwo[i]
                transformedDividend -= doubles[i]
            }
        }

        return if (negatives != 1) -quotient else quotient
    }
}