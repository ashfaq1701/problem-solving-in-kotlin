package ae.medium.cycleInGraph.solution1

fun cycleInGraph(edges: List<List<Int>>): Boolean {
    val numNodes = edges.size

    val visited = MutableList(numNodes) { false }
    val currentlyInStack = MutableList(numNodes) { false }

    for (node in 0 until numNodes) {
        if (visited[node]) {
            continue
        }

        if (isNodeInCycle(node, edges, visited, currentlyInStack)) {
            return true
        }
    }

    return false
}

fun isNodeInCycle(node: Int, edges: List<List<Int>>, visited: MutableList<Boolean>, currentlyInStack: MutableList<Boolean>): Boolean {
    visited[node] = true
    currentlyInStack[node] = true

    for (neighbor in edges[node]) {
        if (!visited[neighbor]) {
            if (isNodeInCycle(neighbor, edges, visited, currentlyInStack)) {
                return true
            }
        } else if (currentlyInStack[neighbor]) {
            return true
        }
    }

    currentlyInStack[node] = false
    return false
}