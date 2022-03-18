package leetcode.maximumSwap.solution1

class Solution {

    fun maximumSwap(num: Int): Int {
        val digits = getDigits(num)

        for (i in 0 until digits.size) {
            var maxNum = Int.MIN_VALUE
            var maxIdx = -1

            for (j in i + 1 until digits.size) {
                if (digits[j] >= maxNum) {
                    maxNum = digits[j]
                    maxIdx = j
                }
            }

            if (maxIdx != -1 && digits[maxIdx] > digits[i]) {
                digits[i] = digits[maxIdx].also {
                    digits[maxIdx] = digits[i]
                }

                break
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