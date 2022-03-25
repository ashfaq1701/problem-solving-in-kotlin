package leetcode.multiplyStrings.solution1

class Solution {
    fun multiply(num1: String, num2: String): String {
        var result = ""
        for (i in num2.indices.reversed()) {
            val singleMult = singleMultiply(num1, num2[i] - '0', num2.lastIndex - i)
            result = add(result, String(singleMult.toCharArray()))
        }

        val zeroCount = result.count { it == '0' }

        return if (zeroCount == result.length) "0" else result
    }

    private fun singleMultiply(num1: String, digit2: Int, zeroesToAdd: Int): List<Char> {
        val result = MutableList(zeroesToAdd) { '0' }

        var carry = 0

        for (i in num1.indices.reversed()) {
            val digit = num1[i] - '0'
            val mult = digit * digit2 + carry
            result.add('0' + (mult % 10))
            carry = mult / 10
        }

        if (carry > 0) {
            result.add('0' + carry)
        }

        result.reverse()

        return result
    }

    private fun add(num1: String, num2: String): String {
        var i = num1.lastIndex
        var j = num2.lastIndex

        val result = mutableListOf<Char>()

        var carry = 0
        while (i >= 0 || j >= 0) {
            val num1Digit = if (i >= 0) {
                num1[i] - '0'.also {
                    i -= 1
                }
            } else 0

            val num2Digit = if (j >= 0) {
                num2[j] - '0'.also {
                    j -= 1
                }
            } else 0

            val sum = num1Digit + num2Digit + carry
            result.add('0' + (sum % 10))
            carry = sum / 10
        }

        if (carry > 0) {
            result.add('0' + carry)
        }

        return String(result.reversed().toCharArray())
    }
}