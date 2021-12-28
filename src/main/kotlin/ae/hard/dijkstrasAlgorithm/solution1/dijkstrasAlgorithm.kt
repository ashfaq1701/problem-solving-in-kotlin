package ae.hard.dijkstrasAlgorithm.solution1

import kotlin.math.min

fun dijkstrasAlgorithm(start: Int, edges: List<List<List<Int>>>): List<Int> {
    val numVertices = edges.size

    // Set all node's min distances to infinity (we don't know their distances yet)
    val minDistances = MutableList(numVertices) { Integer.MAX_VALUE }

    // We know the starting node's min distance. It's 0.
    minDistances[start] = 0

    val visited = mutableSetOf<Int>()

    while (visited.size < numVertices) {

        // Get a node which is unvisited and has minimum distance
        val (minDistanceVertex, minDistance) = getUnvisitedMinDistanceVertex(minDistances, visited)

        // This node has infinity distance. All it's connected nodes will have infinity distance too because they will pass through this node.
        if (minDistance == Integer.MAX_VALUE)
            break

        // Insert this node in visited set.
        visited.add(minDistanceVertex)

        // For all of it's connected vertices
        for ((destination, distanceToDestination) in edges[minDistanceVertex]) {
            // This node was already visited before, so must had a shorter path
            if (destination in visited) {
                continue
            }

            // Calculate minDistances to the connected node, current minDistance + it's distance from current node.
            minDistances[destination] = min(minDistances[destination], minDistance + distanceToDestination)
        }
    }

    return minDistances.map { distance ->
        if (distance == Integer.MAX_VALUE) -1 else distance
    }
}

fun getUnvisitedMinDistanceVertex(minDistances: List<Int>, visited: Set<Int>): Pair<Int, Int> {
    var minDistance = Integer.MAX_VALUE
    var minDistanceVertex = -1

    for (idx in 0 .. minDistances.lastIndex) {
        val distance = minDistances[idx]

        if (idx in visited) continue

        if (distance <= minDistance) {
            minDistanceVertex = idx
            minDistance = distance
        }
    }

    return minDistanceVertex to minDistance
}
