package leetcode.addStrings.solution1

class Solution {
    fun addStrings(num1: String, num2: String): String {
        var i1 = num1.lastIndex
        var i2 = num2.lastIndex

        val result = mutableListOf<Int>()
        var carry = 0

        while (i1 >= 0 || i2 >= 0) {
            val n1 = if (i1 >= 0) num1[i1] - '0' else 0
            val n2 = if (i2 >= 0) num2[i2] - '0' else 0

            val sum = n1 + n2 + carry
            result.add(sum % 10)
            carry = sum / 10

            i1 -= 1
            i2 -= 1
        }

        if (carry > 0) {
            result.add(carry)
        }

        return String(result.reversed().map { '0' + it }.toCharArray())
    }
}