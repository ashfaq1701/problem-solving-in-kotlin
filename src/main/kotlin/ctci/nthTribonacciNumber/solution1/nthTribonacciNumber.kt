package ctci.nthTribonacciNumber.solution1

class Solution {

    var cache = mutableListOf(0, 1, 1)

    fun tribonacci(n: Int): Int {
        if (n < cache.size) return cache[n]

        for (i in cache.size .. n) {
            cache.add(cache[i - 1] + cache[i - 2] + cache[i - 3])
        }

        return cache[n]
    }
}