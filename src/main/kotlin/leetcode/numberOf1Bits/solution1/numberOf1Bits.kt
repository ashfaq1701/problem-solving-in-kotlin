package leetcode.numberOf1Bits.solution1

class Solution {
    // you need treat n as an unsigned value
    fun hammingWeight(n: Int) : Int {
        var oneCount = 0
        var currentN = n

        for (i in 0 until 32) {
            if (currentN and 1 == 1) {
                oneCount += 1
            }

            currentN = currentN shr 1
        }

        return oneCount
    }
}
