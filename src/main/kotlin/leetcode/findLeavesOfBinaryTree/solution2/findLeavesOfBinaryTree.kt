package leetcode.findLeavesOfBinaryTree.solution2

import kotlin.math.max

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {

    val nodeLists = mutableListOf<MutableList<Int>>()

    fun findLeaves(root: TreeNode?): List<List<Int>> {
        findLeavesHelper(root)
        return nodeLists
    }

    fun findLeavesHelper(node: TreeNode?): Int {
        if (node == null) return -1

        val leftHeight = findLeavesHelper(node.left)
        val rightHeight = findLeavesHelper(node.right)

        val currentHeight = max(leftHeight, rightHeight) + 1

        if (nodeLists.size == currentHeight) {
            nodeLists.add(mutableListOf())
        }
        nodeLists[currentHeight].add(node.`val`)

        return currentHeight
    }
}