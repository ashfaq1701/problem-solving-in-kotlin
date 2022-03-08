package facebook.recommendation.bfsShortestReach.solution1

import java.util.LinkedList

fun bfs(n: Int, m: Int, edges: Array<Array<Int>>, s: Int): Array<Int> {
    val graph = MutableList(n + 1) { mutableListOf<Int>() }
    for ((x, y) in edges) {
        graph[x].add(y)
        graph[y].add(x)
    }

    val distances = MutableList(n + 1) { -1 }
    val queue = LinkedList<Pair<Int, Int>>()
    val visited = mutableSetOf<Int>()

    queue.add(s to 0)

    while (queue.isNotEmpty()) {
        val (currentNode, currentDistance) = queue.poll()

        if (currentNode in visited) continue

        visited.add(currentNode)
        if (distances[currentNode] == -1) {
            distances[currentNode] = currentDistance
        }

        for (adj in graph[currentNode]) {
            if (adj !in visited) {
                queue.add(adj to currentDistance + 6)
            }
        }
    }

    val result = mutableListOf<Int>()
    for (node in 1 .. n) {
        if (node == s) continue
        result.add(distances[node])
    }

    return result.toTypedArray()
}
