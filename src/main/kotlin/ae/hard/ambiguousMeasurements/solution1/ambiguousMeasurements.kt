package ae.hard.ambiguousMeasurements.solution1

import kotlin.math.max

fun ambiguousMeasurements(measuringCups: List<List<Int>>, low: Int, high: Int): Boolean {
    // Let range we need to measure is [2200, 2000]
    // Let us think we have two cups [[200, 300], [400, 500]]
    //                       2200, 2000
    //                          /  \
    //                         /    \
    //      Considering [200, 300] Considering [400, 500]
    //                       /        \
    //                 [2000, 1700] [1800, 1500]
    //                     /            \
    //                    /              \
    //      Considering [400, 500]  Considering [200, 300]
    //                 /                   \
    //            [1600, 1200]           [1600, 1200]
    // From above we see that we arrived at [1600, 1200] from both of the branches.
    // To avoid such issue we have to implement memoization.
    return canMeasureInRange(measuringCups, low, high, mutableMapOf())
}

fun canMeasureInRange(measuringCups: List<List<Int>>, low: Int, high: Int, memo: MutableMap<String, Boolean>): Boolean {
    val key = createHashableKey(low, high)

    // If we found the result with current low and high in memo, then return it
    if (memo.containsKey(key)) return memo[key]!!

    // Initialize can measure with false
    var canMeasure = false

    // We will keep recursing until both low and high reduces to 0, which is the base case.
    if (low == 0 && high == 0) return false

    // Then try with each measuring cup and try to measure with it
    for ((measuringCupLow, measuringCupHigh) in measuringCups) {
        // We can measure if measuring cup lies within the range defined by low and high
        // As example [100, 200] cup can measure   [50, 250]
        //            [100, 200] cup can measure   [100, 200]
        //            [100, 200] cup can't measure [1800, 2000]
        //            [100, 200] cup can't measure [150, 250]
        //            [100, 200] cup can't measure [150, 180]
        if (low <= measuringCupLow && high >= measuringCupHigh) {
            canMeasure = true
            break
        }

        // newLow is current low minus the current cup's low mark
        // newHigh is current high minus the current cup's high mark
        // both newLow and newHigh should be bounded by 0
        // Because practically there are no measuments less than 0.
        // So if we have for example [-10, 10], we can make it [0, 10]. Similarly if we have [100, -10] we can cap it to [100, 0].
        // Because they are the same thing as definitely they will be less than any cup's low mark.
        val newLow = max(low - measuringCupLow, 0)
        val newHigh = max(high - measuringCupHigh, 0)

        // Call canMeasureInRange recursively with newLow, newHigh
        canMeasure = canMeasureInRange(measuringCups, newLow, newHigh, memo)
        // If any of the recursive calls return true that means we can measure the volume. No need to do further computation. Store it in memo and pop true to the above layers.
        if (canMeasure) break
    }

    // Store the result in memo.
    memo[key] = canMeasure

    return memo[key]!!
}

fun createHashableKey(low: Int, high: Int): String {
    return "$low-$high"
}
