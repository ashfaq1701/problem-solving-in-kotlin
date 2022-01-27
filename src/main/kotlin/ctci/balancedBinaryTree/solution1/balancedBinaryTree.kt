package ctci.balancedBinaryTree.solution1

import kotlin.math.abs
import kotlin.math.max

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun isBalanced(root: TreeNode?): Boolean {
        return isBalancedHelper(root).first
    }

    fun isBalancedHelper(node: TreeNode?): Pair<Boolean, Int> {
        if (node == null) {
            return true to -1
        }

        val (isLeftHeightBalanced, leftHeight) = isBalancedHelper(node.left)
        val (isRightHeightBalanced, rightHeight) = isBalancedHelper(node.right)

        val subtreeDiffInHeight = abs(leftHeight - rightHeight)
        val isBalanced = isLeftHeightBalanced && isRightHeightBalanced && subtreeDiffInHeight <= 1
        val currentHeight = max(leftHeight, rightHeight) + 1

        return isBalanced to currentHeight
    }
}