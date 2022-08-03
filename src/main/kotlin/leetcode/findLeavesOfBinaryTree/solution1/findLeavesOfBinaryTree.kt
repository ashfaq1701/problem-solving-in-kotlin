package leetcode.findLeavesOfBinaryTree.solution1

import kotlin.math.max

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {

    val nodeHeightPairs = mutableListOf<Pair<Int, Int>>()

    fun findLeaves(root: TreeNode?): List<List<Int>> {
        findLeavesHelper(root)
        val nodesMap = mutableMapOf<Int, MutableList<Int>>()

        for ((height, nodeVal) in nodeHeightPairs) {
            if (height !in nodesMap) {
                nodesMap[height] = mutableListOf()
            }

            nodesMap[height]!!.add(nodeVal)
        }

        return nodesMap.values.toList()
    }

    fun findLeavesHelper(node: TreeNode?): Int {
        if (node == null) return -1

        val leftHeight = findLeavesHelper(node.left)
        val rightHeight = findLeavesHelper(node.right)

        val currentHeight = max(leftHeight, rightHeight) + 1

        nodeHeightPairs.add(currentHeight to node.`val`)

        return currentHeight
    }
}