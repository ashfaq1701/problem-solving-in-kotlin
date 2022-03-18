package leetcode.maximumSwap.solution2

class Solution {

    fun maximumSwap(num: Int): Int {
        val digits = getDigits(num)

        val lastIndices = MutableList<Int>(10) { 0 }

        for (idx in digits.indices) {
            lastIndices[digits[idx]] = idx
        }

        for (i in digits.indices) {
            for (greaterDigit in 9 downTo digits[i] + 1) {
                if (lastIndices[greaterDigit] > i) {
                    val lastIndex = lastIndices[greaterDigit]
                    digits[i] = digits[lastIndex].also {
                        digits[lastIndex] = digits[i]
                    }
                    return getNum(digits)
                }
            }
        }

        return getNum(digits)
    }

    fun getDigits(num: Int): MutableList<Int> {
        val digits = mutableListOf<Int>()

        var current = num
        while (current != 0) {
            digits.add(current % 10)
            current /= 10
        }

        digits.reverse()
        return digits
    }

    fun getNum(digits: List<Int>): Int {
        var current = 0

        for (digit in digits) {
            current = current * 10 + digit
        }

        return current
    }
}