package leetcode.binaryTreeVerticalOrderTraversal.solution1

import java.util.*

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return listOf()

        val levelMap = TreeMap<Int, MutableList<Int>>()
        val queue = LinkedList<Pair<TreeNode, Int>>()

        queue.add(root to 0)

        while (queue.isNotEmpty()) {
            val (node, level) = queue.poll()

            if (level !in levelMap) {
                levelMap[level] = mutableListOf()
            }

            levelMap[level]!!.add(node.`val`)

            if (node.left != null) {
                queue.add(node.left!! to level - 1)
            }

            if (node.right != null) {
                queue.add(node.right!! to level + 1)
            }
        }

        return levelMap.values.map { it.toList() }
    }
}