package leetcode.hammingDistance.solution1

class Solution {
    fun hammingDistance(x: Int, y: Int): Int {
        var currentX = x
        var currentY = y

        var hammingDist = 0

        for (i in 0 until 32) {
            val currentXIsOne = currentX and 1 == 1
            val currentYIsOne = currentY and 1 == 1

            if (currentXIsOne != currentYIsOne) {
                hammingDist += 1
            }

            currentX = currentX shr 1
            currentY = currentY shr 1
        }

        return hammingDist
    }
}