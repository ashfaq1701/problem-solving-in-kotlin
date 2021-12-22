package ae.veryHard.aStarAlgorithm.solution1

fun aStarAlgorithm(startRow: Int, startCol: Int, endRow: Int, endCol: Int, graph: List<List<Int>>): List<List<Int>> {
    // G, H and F Score
    // G Score = Distance from start node
    // H Score = Estimated distance from end node (Manhattan distance)
    // F Score = G Score (Distance from start node) + H Score (Estimated distance from end node)

    // Build graph
    val nodeGraph = buildGraph(graph)

    // Get start and end node
    val startNode = nodeGraph[startRow][startCol]
    val endNode = nodeGraph[endRow][endCol]

    // Start node's G Score is 0
    startNode.distanceFromStart = 0

    // Calculate H Score for start node
    startNode.estimatedDistanceToEnd = calculateManhattanDistance(startNode, endNode)

    // We need a heap because at each step we need to find the node using which the start node will be closest to the destination
    val nodesToVisit = MinHeap()
    // Insert the starting node to the heap
    nodesToVisit.insert(startNode)

    // Until the heap is empty
    while (!nodesToVisit.isEmpty()) {
        // Remove the root node from the heap which **using which the start node will be closest to the end node**
        val currentNode = nodesToVisit.remove()

        // If the node popped off is the end node then break. We have reached the destination
        if (currentNode == endNode)
            break

        // Get all of the neighboring nodes of the current node
        val neighbors = getNeighbors(currentNode, nodeGraph)

        // Distance to neighbor node is the distance of the current node from the start node + 1
        val distanceToNeighbor = currentNode.distanceFromStart + 1

        for (neighbor in neighbors) {

            // If neighbor value is 1 then this is an obstacle. This can't be passed through, so continue.
            if (neighbor.value == 1)
                continue

            // The the newly calculated distance is greater than or equal to the already existing distance then it's of no use, discard it
            if (distanceToNeighbor >= neighbor.distanceFromStart)
                continue

            // Set neighbor's distance from start is equal to the distance to neighbor
            neighbor.distanceFromStart = distanceToNeighbor

            // Using this neighbor the estimated distance from start node to destination node is *** distance to neighbor + manhattan distance from this node to destination ***
            neighbor.estimatedDistanceToEnd = distanceToNeighbor + calculateManhattanDistance(neighbor, endNode)

            // Neighbor came from the current node so mark it with that. Needed for build the sequence to the start node.
            neighbor.cameFrom = currentNode

            // If the heap contains this node then update it
            if (nodesToVisit.contains(neighbor)) {
                nodesToVisit.update(neighbor)
            } else {

                // Otherwise insert it
                nodesToVisit.insert(neighbor)
            }
        }
    }

    return buildResult(endNode, nodeGraph)
}

fun buildGraph(nodes: List<List<Int>>): List<List<Node>> {

    val rows = nodes.size
    val cols = nodes[0].size

    val graph = MutableList(nodes.size) { mutableListOf<Node>() }

    for (r in 0 until rows) {
        for (c in 0 until cols) {
            graph[r].add(Node(r, c, nodes[r][c]))
        }
    }

    return graph.map { it.toList() }
}

fun calculateManhattanDistance(a: Node, b: Node): Int {
    return (a.row - b.row) + (a.col - b.col)
}

fun getNeighbors(current: Node, graph: List<List<Node>>): List<Node> {
    val numRows = graph.size
    val numCols = graph[0].size

    val currentRow = current.row
    val currentCol = current.col

    val neighbors = mutableListOf<Node>()

    if (currentRow - 1 >= 0) {
        neighbors.add(graph[currentRow - 1][currentCol])
    }

    if (currentCol - 1 >= 0) {
        neighbors.add(graph[currentRow][currentCol - 1])
    }

    if (currentRow + 1 < numRows) {
        neighbors.add(graph[currentRow + 1][currentCol])
    }

    if (currentCol + 1 < numCols) {
        neighbors.add(graph[currentRow][currentCol + 1])
    }

    return neighbors
}

fun buildResult(endNode: Node, graph: List<List<Node>>): List<List<Int>> {
    // If end node doesn't contain any node which it came from then end node is not reachable.
    if (endNode.cameFrom == null) {
        return listOf()
    }

    // Backtrack through the result starting from the end node
    val result = mutableListOf<List<Int>>()
    var currentNode: Node? = endNode

    while (currentNode != null) {
        result.add(listOf(currentNode.row, currentNode.col))
        currentNode = currentNode.cameFrom
    }

    return result.reversed()
}

data class Node(val row: Int, val col: Int, val value: Int) {
    val id = "$row-$col"

    // Distance from start node - G Score
    var distanceFromStart = Integer.MAX_VALUE

    // Estimated (or Manhattan) distance to end node - H Score
    var estimatedDistanceToEnd = Integer.MAX_VALUE

    // F Score = G Score + H Score

    var cameFrom: Node? = null
}

// This heap's sorting parameter is estimated distance to end (H Score)
class MinHeap {

    val heap = mutableListOf<Node>()

    // This is a map between node's id and it's index in the underlying heap array
    val nodePositionsToHeap = mutableMapOf<String, Int>()

    fun siftDown(currentIdx: Int, endIdx: Int, heap: List<Node>) {

        var mutableCurrentIdx = currentIdx

        var childOneIdx = mutableCurrentIdx * 2 + 1

        while (childOneIdx <= endIdx) {
            val childTwoIdx = if (mutableCurrentIdx * 2 + 2 <= endIdx) mutableCurrentIdx * 2 + 2 else -1
            var idxToSwap = if (childTwoIdx != -1 && heap[childTwoIdx].estimatedDistanceToEnd < heap[childOneIdx].estimatedDistanceToEnd) {
                childTwoIdx
            } else childOneIdx

            if (heap[idxToSwap].estimatedDistanceToEnd < heap[mutableCurrentIdx].estimatedDistanceToEnd) {
                swap(mutableCurrentIdx, idxToSwap)
                mutableCurrentIdx = idxToSwap
                childOneIdx = mutableCurrentIdx * 2 + 1
            } else {
                // Already in the correct position
                return
            }
        }
    }

    fun siftUp(currentIdx: Int) {
        var mutableCurrentIdx = currentIdx

        var parentIdx = (mutableCurrentIdx - 1) / 2

        while (mutableCurrentIdx > 0 && heap[mutableCurrentIdx].estimatedDistanceToEnd < heap[parentIdx].estimatedDistanceToEnd) {
            swap(mutableCurrentIdx, parentIdx)
            mutableCurrentIdx = parentIdx
            parentIdx = (mutableCurrentIdx - 1) / 2
        }
    }

    fun remove(): Node {
        swap(heap.lastIndex, 0)
        val nodeToRemove = heap.removeAt(heap.lastIndex)
        siftDown(0, heap.lastIndex, heap)
        return nodeToRemove
    }

    fun insert(node: Node) {
        heap.add(node)
        nodePositionsToHeap[node.id] = heap.lastIndex
        siftUp(heap.lastIndex)
    }

    fun isEmpty(): Boolean = heap.size == 0

    fun contains(node: Node): Boolean {
        // We can search if the item exists in constant time by searching for it in the hash map.
        return nodePositionsToHeap.containsKey(node.id)
    }

    fun update(node: Node) {
        // The node object is already updated.
        // So here update means that we need to sift it up to the appropriate position.
        siftUp(nodePositionsToHeap[node.id]!!)
    }

    fun swap(i: Int, j: Int) {
        val temp = heap[i]
        heap[i] = heap[j]
        heap[j] = temp

        // The items are swapped in the array. i went to j and j went to i.
        // So now in the map we need to update their index.
        // i and j are swapped before. So now assign i's value to i and j's value to j.
        nodePositionsToHeap[heap[i].id] = i
        nodePositionsToHeap[heap[j].id] = j
    }
}
