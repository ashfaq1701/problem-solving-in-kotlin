package ae.veryHard.detectArbitrage.solution1

import kotlin.math.log

fun detectArbitrage(exchangeRates: List<List<Double>>): Boolean {
    // Belman-Ford algorithm can detect negative weight cycle.
    // For a graph like below,
    //     1
    //    / \
    //   a   b
    //   \   /
    //    \ /
    //     2
    // An arbitrage will mean,
    // a * b > 1 = log(a * b) > log(1) = log(a) + log(b) > 0
    // Which is a positive weight cycle. Belman-Ford algorithm is used to detect negative weight cycles.
    // So we need to change it to -(log(a) + log(b)) < 0
    // Or -log(a) - log(b) < 0
    // Hence we need to convert all of the edge weights to their negative logarithms.
    val logExchangeRates = convertToLogMatrix(exchangeRates)
    return foundNegativeWeightCycle(logExchangeRates, 0)
}

fun convertToLogMatrix(exchangeRates: List<List<Double>>): List<List<Double>> {
    return exchangeRates.map { singleCurrencyRates ->
        singleCurrencyRates.map { -log(it, 10.0) }
    }
}

// Belman-Ford algorithm
fun foundNegativeWeightCycle(graph: List<List<Double>>, start: Int): Boolean {
    val n = graph.size

    // Initially all of the nodes will have infinity distance
    // except the start node, which will have 0 distance
    val distances = MutableList(n) { Double.MAX_VALUE }
    distances[start] = 0.0

    // For a graph with n nodes from any source to any destination
    // the minimum path will contain at most (n - 1) edges.
    // Hence we need to perform relaxation (n - 1) times.
    // After (n - 1) steps all the nodes will reach their minimum distances from the start node
    // And it will be stabilized.
    // If we don't have negative weighted cycle
    // then any further relaxation step won't change the distances anymore.
    for (i in 0 until n - 1) {
        // But if within (n - 1) steps relaxation
        // doesn't change distances anymore then
        // prematurely we can say that the graph
        // doesn't contain any negative weighted cycles.
        // And any more relaxation will not change the distances anymore.
        if (!relaxEdgesAndUpdateDistances(graph, distances))
            return false
    }

    // At this point the graph is stabilized.
    // If we perform one more relaxation and the distances get updated
    // Then we have a negative weighted cycle. So we will return true.
    return relaxEdgesAndUpdateDistances(graph, distances)
}

fun relaxEdgesAndUpdateDistances(graph: List<List<Double>>, distances: MutableList<Double>): Boolean {
    // We need to keep track if any distance got updated in this step.
    // Initially it is started as false.
    var updated = false

    // We consider every row as a source
    // In the row the elements themselves are edge weights
    graph.forEachIndexed { sourceIdx, edges ->
        // Now each edge index is destination index
        // And each element is a edge weight
        edges.forEachIndexed { destinationIdx, edgeWeight ->

            // If we use this source to arrive this destination using,
            // distination distance = current source's distance + destination's distance from current source
            // If this distance is smaller than destination's current distance
            // Then we need to update destination's current distance.
            if (distances[sourceIdx] + edgeWeight < distances[destinationIdx]) {

                distances[destinationIdx] = distances[sourceIdx] + edgeWeight
                // And we will mark updated to true, because we did update a distance in this step.
                updated = true
            }
        }
    }

    // We will return if we update any distance in this step.
    return updated
}