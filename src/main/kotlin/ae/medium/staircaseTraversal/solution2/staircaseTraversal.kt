package ae.medium.staircaseTraversal.solution2

import kotlin.math.min

// O(n * k) time | O(n) space
fun staircaseTraversal(height: Int, maxSteps: Int): Int {
    val waysToTop = MutableList(height + 1) { 0 }
    waysToTop[0] = 1
    waysToTop[1] = 1

    for (currentHeight in 2 .. height) {

        for (step in 1 .. min(currentHeight, maxSteps)) {
            waysToTop[currentHeight] += waysToTop[currentHeight - step]
        }
    }

    return waysToTop.last()
}