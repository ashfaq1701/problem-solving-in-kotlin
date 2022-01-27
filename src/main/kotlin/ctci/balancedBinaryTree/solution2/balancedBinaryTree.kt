package ctci.balancedBinaryTree.solution2

import kotlin.math.abs
import kotlin.math.max

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun isBalanced(root: TreeNode?): Boolean {
        if (root == null) return true

        return abs(getHeight(root.left) - getHeight(root.right)) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right)
    }

    fun getHeight(node: TreeNode?): Int {
        if (node == null) return -1

        return max(getHeight(node.left), getHeight(node.right)) + 1
    }
}