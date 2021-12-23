package ae.veryHard.twoEdgeConnectedGraph.solution1

import kotlin.math.min

fun twoEdgeConnectedGraph(edges: List<List<Int>>): Boolean {
    // Empty graph is two edge connected
    if (edges.isEmpty()) return true

    // Vector which keeps track of the arrival time
    val arrivalTimes = MutableList<Int>(edges.size) { -1 }

    // If back to the beginning of the dfs we have a -1 bubbled up, then return false
    if (getMinimumArrivalTimeToAncestor(0, -1, 0, arrivalTimes, edges) == -1) {
        return false
    }

    // If all of the edges were visited in the process then return true. Else the graph has detached nodes and not even connected.
    return ifAllEdgesVisited(arrivalTimes)
}

fun getMinimumArrivalTimeToAncestor(currentNode: Int, parentNode: Int, currentTime: Int, arrivalTimes: MutableList<Int>, edges: List<List<Int>>): Int {
    // currentNode is being visited at currentTime
    arrivalTimes[currentNode] = currentTime

    // Assumption. Minimum arrival time for current node is the current time. Because a node can certainly have a back edge to itself
    // Also we need to get back edge to any of the current node's ancestors.
    // That's why we set minimumArrivalTime to currentTime.
    var minimumArrivalTime = currentTime

    // Iterate to all of the edges from the current node
    for (destination in edges[currentNode]) {
        // If we didn't visit this node before. i.e. This is a tree edge
        if (arrivalTimes[destination] == -1) {
            minimumArrivalTime = min(
                minimumArrivalTime,
                getMinimumArrivalTimeToAncestor(destination, currentNode, currentTime + 1, arrivalTimes, edges)
            )
        }
        // If the node was visited before but it is not the parent of the current node, i.e. this is a back edge
        // So we need the minimum between minumumArrivalTime of the back edge and minumumArrivalTime of current node.
        else if (destination != parentNode) {
            // This means that consider back edge but ignore forward edge
            minimumArrivalTime = min(minimumArrivalTime, arrivalTimes[destination])
        }
    }

    // It didn't find any back edge throughout the dfs "to any of current node's ancestor"
    // The logic of this algorithm. If any node stays in it's current arrival time after the dfs is done and it's not the starting node, then it has a bridge.
    // And as a result the graph is not two edge connected. The -1 value will be bubbled up throughout the recursion, because min(-1, any_positive_value) is -1.
    if (minimumArrivalTime == currentTime && parentNode != -1) {
        return -1
    }

    return minimumArrivalTime
}

fun ifAllEdgesVisited(arrivalTimes: List<Int>): Boolean {
    return arrivalTimes.find { it == -1 } == null
}
