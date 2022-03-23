package ctci.addBinary.solution2

import java.math.BigInteger

class Solution {
    fun addBinary(a: String, b: String): String {
        var aNum = BigInteger(a, 2)
        var bNum = BigInteger(b, 2)
        val zero = BigInteger.ZERO

        while (bNum != zero) {
            val sumWithoutCarry = aNum xor bNum
            val carry = (aNum and bNum) shl 1
            aNum = sumWithoutCarry
            bNum = carry
        }

        return aNum.toString(2)
    }
}