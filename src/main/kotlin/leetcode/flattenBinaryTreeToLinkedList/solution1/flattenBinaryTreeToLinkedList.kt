package leetcode.flattenBinaryTreeToLinkedList.solution1

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun flatten(root: TreeNode?): Unit {
        flattenHelper(root)
    }

    fun flattenHelper(node: TreeNode?): TreeNode? {
        if (node == null) return null

        if (node.left == null && node.right == null) {
            return node
        }

        val leftTail = flattenHelper(node.left)
        val rightTail = flattenHelper(node.right)

        if (leftTail != null) {
            leftTail.right = node.right
            node.right = node.left
            node.left = null
        }

        return if (rightTail != null) rightTail else leftTail
    }
}