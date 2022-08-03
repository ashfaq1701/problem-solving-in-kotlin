package leetcode.allPathsFromSourceLeadToDestination.solution1

class Solution {

    fun leadsToDestination(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val adjList = buildAdjList(n, edges)

        if (adjList[destination].isNotEmpty()) return false

        return dfsHelper(source, destination, adjList, MutableList(n) { false })
    }

    private fun dfsHelper(currentNode: Int, destNode: Int, adjList: List<List<Int>>, visited: MutableList<Boolean>): Boolean {
        if (currentNode == destNode) return true

        if (visited[currentNode]) return false

        if (adjList[currentNode].isEmpty()) return false

        visited[currentNode] = true

        for (adjNode in adjList[currentNode]) {
            if (!dfsHelper(adjNode, destNode, adjList, visited)) {
                return false
            }
        }

        visited[currentNode] = false
        return true
    }

    private fun buildAdjList(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val adjList = MutableList(n) { mutableListOf<Int>() }

        for ((fromNode, toNode) in edges) {
            adjList[fromNode].add(toNode)
        }

        return adjList
    }
}