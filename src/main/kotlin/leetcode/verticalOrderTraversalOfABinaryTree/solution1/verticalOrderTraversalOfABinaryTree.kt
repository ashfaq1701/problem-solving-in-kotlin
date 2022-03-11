package leetcode.verticalOrderTraversalOfABinaryTree.solution1

import java.util.*

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

data class NodeInfo(val row: Int, val col: Int, val node: TreeNode)

class Solution {
    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        if (root == null) return listOf(listOf())

        val nodesMap = TreeMap<Int, TreeMap<Int, MutableList<Int>>>()
        val queue = LinkedList<NodeInfo>()
        queue.add(NodeInfo(0, 0, root))

        while (queue.isNotEmpty()) {
            val (currentRow, currentCol, currentNode) = queue.poll()

            if (currentCol !in nodesMap) {
                nodesMap[currentCol] = TreeMap()
            }

            if (currentRow !in nodesMap[currentCol]!!) {
                nodesMap[currentCol]!![currentRow] = mutableListOf()
            }

            nodesMap[currentCol]!![currentRow]!!.add(currentNode.`val`)

            if (currentNode.left != null) {
                queue.add(NodeInfo(currentRow + 1, currentCol - 1, currentNode.left!!))
            }

            if (currentNode.right != null) {
                queue.add(NodeInfo(currentRow + 1, currentCol + 1, currentNode.right!!))
            }
        }

        return nodesMap.values.map { it.flatMap { (_, list) -> list.sorted() } }
    }
}