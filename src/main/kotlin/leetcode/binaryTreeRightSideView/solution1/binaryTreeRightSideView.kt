package leetcode.binaryTreeRightSideView.solution1

import java.util.LinkedList

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun rightSideView(root: TreeNode?): List<Int> {
        val items = mutableListOf<Int>()

        if (root == null) return items

        items.add(root.`val`)

        val queue = LinkedList<TreeNode?>()
        queue.add(root)
        queue.add(null)

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current == null) {
                if (queue.isNotEmpty()) {
                    items.add(queue.peek()!!.`val`)
                    queue.add(null)
                }

                continue
            }

            if (current.right != null) {
                queue.add(current.right!!)
            }

            if (current.left != null) {
                queue.add(current.left!!)
            }
        }

        return items
    }
}