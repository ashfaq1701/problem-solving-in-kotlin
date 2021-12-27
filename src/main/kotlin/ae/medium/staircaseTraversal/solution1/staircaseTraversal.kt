package ae.medium.staircaseTraversal.solution1

import kotlin.math.min

// O(n * k) time | O(n) space
fun staircaseTraversal(height: Int, maxSteps: Int): Int {
    val memo = mutableMapOf(0 to 1, 1 to 1)
    return numberOfWaysToTop(height, maxSteps, memo)
}

fun numberOfWaysToTop(height: Int, maxSteps: Int, memo: MutableMap<Int, Int>): Int {
    if (memo.containsKey(height)) {
        return memo[height]!!
    }

    var numberOfWays = 0
    for (step in 1 .. min(height, maxSteps)) {
        numberOfWays += numberOfWaysToTop(height - step, maxSteps, memo)
    }

    memo[height] = numberOfWays
    return memo[height]!!
}