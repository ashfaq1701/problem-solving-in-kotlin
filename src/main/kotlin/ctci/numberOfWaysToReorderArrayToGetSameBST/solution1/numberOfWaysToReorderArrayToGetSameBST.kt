package ctci.numberOfWaysToReorderArrayToGetSameBST.solution1

import java.math.BigInteger

class Solution {
    fun numOfWays(nums: IntArray): Int {
        val factorials = computeFactorial(nums.size)
        return (computeWays(nums, factorials) - 1.toBigInteger()).toInt()
    }

    fun computeWays(nums: IntArray, factorials: List<BigInteger>): BigInteger {
        val mod = 1000000007.toBigInteger()
        if (nums.size <= 2) return 1.toBigInteger()

        val root = nums[0]
        val left = nums.filter { it < root }.toIntArray()
        val right = nums.filter { it > root }.toIntArray()
        return (computeWays(left, factorials) * computeWays(right, factorials) * combination(left.size, right.size, factorials)) % mod
    }

    fun computeFactorial(n: Int): List<BigInteger> {
        val cache = mutableListOf<BigInteger>(0.toBigInteger(), 1.toBigInteger())

        for (i in 2 .. n) {
            cache.add(cache[i - 1] * i.toBigInteger())
        }

        return cache
    }

    fun combination(nL: Int, nR: Int, factorials: List<BigInteger>): BigInteger {
        if (nL == 0 || nR == 0) return 1.toBigInteger()

        return factorials[nL + nR] / (factorials[nL] * factorials[nR])
    }
}