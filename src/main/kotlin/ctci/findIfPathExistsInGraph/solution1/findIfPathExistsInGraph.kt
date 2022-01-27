package ctci.findIfPathExistsInGraph.solution1

class Solution {
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val graph = buildGraph(n, edges)
        val visited = mutableSetOf<Int>()
        return dfsHelper(graph, source, destination, visited)
    }

    fun dfsHelper(graph: List<List<Int>>, current: Int, destination: Int, visited: MutableSet<Int>): Boolean {
        if (current == destination) return true

        visited.add(current)

        for (adj in graph[current]) {
            if (adj !in visited && dfsHelper(graph, adj, destination, visited)) {
                return true
            }
        }

        return false
    }

    fun buildGraph(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val graph = MutableList(n) { mutableListOf<Int>() }

        for ((a, b) in edges) {
            graph[a].add(b)
            graph[b].add(a)
        }

        return graph.map { it.toList() }
    }
}