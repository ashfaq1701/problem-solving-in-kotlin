package leetcode.countingBits.solution3

class Solution {
    fun countBits(n: Int): IntArray {
        val ans = MutableList(n + 1) { 0 }
        var x = 0
        var b = 1

        while (b <= n) {

            while (x < b && x + b <= n) {
                ans[x + b] = ans[x] + 1
                x += 1
            }

            x = 0
            b = b shl 1
        }

        return ans.toIntArray()
    }
}