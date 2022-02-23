package leetcode.countingBits.solution2

class Solution {
    // x  = (1001011101)2 = (605)10
    // x' = (100101110)2 = (302){10}
    // We can see that x' differs from x by one bit, because x'
    // can be considered as the result of removing the least significant bit of x.

    fun countBits(n: Int): IntArray {
        val counts = MutableList(n + 1) { 0 }

        for (i in 1 .. n) {
            counts[i] = counts[i shr 1] + (i and 1)
        }

        return counts.toIntArray()
    }
}