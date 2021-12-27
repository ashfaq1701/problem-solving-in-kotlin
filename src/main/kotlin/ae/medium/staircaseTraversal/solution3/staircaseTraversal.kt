package ae.medium.staircaseTraversal.solution3

// O(n) time | O(n) space
fun staircaseTraversal(height: Int, maxSteps: Int): Int {
    var currentNumberOfWays = 0
    val waysToTop = mutableListOf(1)

    for (currentHeight in 1 .. height) {
        val startOfWindow = currentHeight - maxSteps - 1
        val endOfWindow = currentHeight - 1

        if (startOfWindow >= 0) {
            currentNumberOfWays -= waysToTop[startOfWindow]
        }

        currentNumberOfWays += waysToTop[endOfWindow]
        waysToTop.add(currentNumberOfWays)
    }

    return waysToTop.last()
}
