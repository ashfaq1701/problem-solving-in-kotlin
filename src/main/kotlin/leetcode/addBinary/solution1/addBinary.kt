package leetcode.addBinary.solution1

class Solution {
    fun addBinary(a: String, b: String): String {
        var i = a.lastIndex
        var j = b.lastIndex

        var carry = 0
        var result = mutableListOf<Char>()

        while (i >= 0 || j >= 0 || carry > 0) {
            val aBit = if (i >= 0) {
                a[i] - '0'.also {
                    i -= 1
                }
            } else 0

            val bBit = if (j >= 0) {
                b[j] - '0'.also {
                    j -= 1
                }
            } else 0

            val countOfOnes = aBit + bBit + carry
            carry = countOfOnes / 2
            result.add('0' + (countOfOnes % 2))
        }

        return String(result.reversed().toCharArray())
    }
}