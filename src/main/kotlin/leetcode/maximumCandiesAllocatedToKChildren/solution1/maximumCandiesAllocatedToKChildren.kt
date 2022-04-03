package leetcode.maximumCandiesAllocatedToKChildren.solution1

class Solution {
    fun maximumCandies(candies: IntArray, k: Long): Int {
        var candiesSum = 0L
        for (candy in candies) {
            candiesSum += candy.toLong()
        }

        if (candiesSum < k) return 0

        var left = 1
        var right = candies.maxOrNull()!!

        while (left < right) {
            val mid = (left + right + 1) / 2

            val numKids = candiesCanBeDistributedAmong(candies, mid)

            if (numKids < k) {
                right = mid - 1
            } else {
                left = mid
            }
        }

        return left
    }

    private fun candiesCanBeDistributedAmong(candies: IntArray, numCandies: Int): Long {
        var kidCount = 0L

        for (candyPile in candies) {
            kidCount += (candyPile.toLong() / numCandies)
        }

        return kidCount
    }
}