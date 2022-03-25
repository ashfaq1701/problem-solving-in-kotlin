package leetcode.cloneGraph.solution1

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList<Node?>()
}

class Solution {
    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null

        val cache = mutableMapOf<Node, Node>()
        return cloneHelper(node, cache)
    }

    fun cloneHelper(node: Node, cache: MutableMap<Node, Node>): Node {
        if (node in cache) return cache[node]!!

        val clonedNode = Node(node.`val`)
        cache[node] = clonedNode

        for (neighbor in node.neighbors) {
            clonedNode.neighbors.add(cloneHelper(neighbor!!, cache))
        }

        return clonedNode
    }
}