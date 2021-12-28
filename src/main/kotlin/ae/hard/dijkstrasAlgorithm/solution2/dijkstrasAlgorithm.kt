package ae.hard.dijkstrasAlgorithm.solution2

fun dijkstrasAlgorithm(start: Int, edges: List<List<List<Int>>>): List<Int> {
    val numVertices = edges.size

    // Initial min distance of all nodes to be infinity
    // From this array we will never remove items
    val minDistances = MutableList(numVertices) { Integer.MAX_VALUE }
    // Starting node will have a min distance of 0
    minDistances[start] = 0

    // Items from this heap will be removed once they are processed
    val minDistanceItems = (0 .. edges.lastIndex).map {
        Item(it, Int.MAX_VALUE)
    }.toMutableList()

    val minDistanceHeap = MinHeap(minDistanceItems)
    // Starting node will have a distance of 0
    minDistanceHeap.update(start, 0)

    // While we have more items in heap (means that more items in the heap)
    while (!minDistanceHeap.isEmpty()) {

        // Get the vertex of the item with minimum distance and distance to it
        val (minDistanceVertex, currentMinDistance) = minDistanceHeap.remove()!!

        // If the min distance is infinity then no luck. Next items will also be infinity
        if (currentMinDistance == Int.MAX_VALUE) {
            break
        }

        // For every neighbor of this vertex with min distance
        for ((destination, distanceToDestination) in edges[minDistanceVertex]) {

            // Calculate new distance as sum of current min distance and the distance from current node
            val newDistance = currentMinDistance + distanceToDestination

            // Current distance of this destination VERTEX is stored in minDistances
            val currentDistance = minDistances[destination]

            // But our calculated new distance can be better of course
            if (newDistance < currentDistance) {
                // If it is then update the misDistances array
                minDistances[destination] = newDistance

                // And inside the heap update the min distance for the destination VERTEX
                minDistanceHeap.update(destination, newDistance)
            }
        }
    }

    return minDistances.map { distance ->
        if (distance == Integer.MAX_VALUE) -1 else distance
    }
}

data class Item(val vertex: Int, val distance: Int)

open class MinHeap(val array: MutableList<Item>) {

    val heap: MutableList<Item>
    val vertexMap: MutableMap<Int, Int> = mutableMapOf()

    init {
        array.forEachIndexed { idx, item ->
            vertexMap[item.vertex] = idx
        }

        heap = buildHeap(array)
    }


    fun buildHeap(array: MutableList<Item>): MutableList<Item> {
        val firstParentIdx = (array.lastIndex - 1) / 2
        var parentIdx = firstParentIdx

        while (parentIdx >= 0) {
            siftDown(parentIdx, array.lastIndex, array)
            parentIdx -= 1
        }

        return array
    }

    fun siftDown(currentIdx: Int, endIdx: Int, heap: MutableList<Item>) {
        var mutableCurrentIdx = currentIdx

        var childOneIdx = mutableCurrentIdx * 2 + 1

        while (childOneIdx <= endIdx) {
            val childTwoIdx = if (mutableCurrentIdx * 2 + 2 <= endIdx) mutableCurrentIdx * 2 + 2 else -1
            val idxToSwap = if (childTwoIdx != -1 && heap[childTwoIdx].distance < heap[childOneIdx].distance) {
                childTwoIdx
            } else childOneIdx

            if (heap[idxToSwap].distance < heap[mutableCurrentIdx].distance) {
                swap(mutableCurrentIdx, idxToSwap, heap)
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

        while (mutableCurrentIdx > 0 && heap[mutableCurrentIdx].distance < heap[parentIdx].distance) {
            swap(mutableCurrentIdx, parentIdx, heap)
            mutableCurrentIdx = parentIdx
            parentIdx = (mutableCurrentIdx - 1) / 2
        }
    }

    fun isEmpty() = heap.isEmpty()

    fun remove(): Item? {
        swap(heap.lastIndex, 0, heap)
        val nodeToRemove = heap.removeAt(heap.lastIndex)

        val (vertex, _) = nodeToRemove
        // Let's remove the entry (location inside heap array) from vertexMap, because it's no more there
        vertexMap.remove(vertex)

        siftDown(0, heap.lastIndex, heap)
        return nodeToRemove
    }

    fun update(vertex: Int, value: Int) {
        // Which index in the heap array the item is located? Update that item.
        heap[vertexMap[vertex]!!] = Item(vertex, value)
        siftUp(vertexMap[vertex]!!)
    }

    fun swap(i: Int, j: Int, heap: MutableList<Item>) {
        // After this swap
        // Vertex in item at i will be located in index j
        // Vertex in item at j will be located in index i
        vertexMap[heap[i].vertex] = j
        vertexMap[heap[j].vertex] = i
        val temp = heap[i]
        heap[i] = heap[j]
        heap[j] = temp
    }
}
