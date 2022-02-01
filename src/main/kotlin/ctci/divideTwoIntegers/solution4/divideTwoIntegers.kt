package ctci.divideTwoIntegers.solution4

const val HALF_INT_MIN = -1073741824

class Solution {

    // Adding Powers of Two with Bit-Shifting
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
        
        var highestPowerOfTwo = -1
        var highestDouble = transformedDivisor
        
        while (highestDouble >= HALF_INT_MIN && highestDouble + highestDouble >= transformedDividend) {
            highestPowerOfTwo += highestPowerOfTwo
            highestDouble += highestDouble
        }

        var quotient = 0
        
        while (transformedDividend <= transformedDivisor) {
            
            if (highestDouble >= transformedDividend) {
                quotient += highestPowerOfTwo
                transformedDividend -= highestDouble
            }
            
            highestPowerOfTwo = highestPowerOfTwo shr 1
            highestDouble = highestDouble shr 1
        }

        return if (negatives != 1) -quotient else quotient
    }
}