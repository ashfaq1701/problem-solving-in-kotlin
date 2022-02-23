package leetcode.countingBits.solution1

class Solution {
    fun countBits(n: Int): IntArray {
        val countBits = mutableListOf<Int>()

        for (i in 0 .. n) {
            countBits.add(getOneBitCounts(i))
        }

        return countBits.toIntArray()
    }

    fun getOneBitCounts(n: Int): Int {
        var currentN = n

        var oneCount = 0

        for (i in 0 until 32) {
            if (currentN and 1 == 1) {
                oneCount += 1
            }

            currentN = currentN shr 1
        }

        return oneCount
    }
}