package ctci.binaryTreeLevelOrderTraversal.solution1

import java.util.*

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()

        if (root == null) return result

        val queue = LinkedList<TreeNode?>()
        queue.add(root)
        queue.add(null)

        var currentLevelList = mutableListOf<Int>()

        while (queue.isNotEmpty()) {
            val current = queue.pop()

            if (current == null) {
                result.add(currentLevelList)
                currentLevelList = mutableListOf()

                if (queue.isNotEmpty()) {
                    queue.add(null)
                }

                continue
            }

            currentLevelList.add(current.`val`)

            if (current.left != null) {
                queue.add(current.left)
            }

            if (current.right != null) {
                queue.add(current.right)
            }
        }

        return result
    }
}