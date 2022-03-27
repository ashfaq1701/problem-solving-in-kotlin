package leetcode.diameterOfBinaryTree.solution1

import kotlin.math.max

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        return diameterHelper(root).second
    }

    fun diameterHelper(node: TreeNode?): Pair<Int, Int> {
        if (node == null) return -1 to 0

        val (leftBranchLen, leftDiameter) = diameterHelper(node.left)
        val (rightBranchLen, rightDiameter) = diameterHelper(node.right)

        val leftBranchWithCurrentNode = leftBranchLen + 1
        val rightBranchWithCurrentNode = rightBranchLen + 1

        val maxBranchLenWithCurrentNode = max(leftBranchWithCurrentNode, rightBranchWithCurrentNode)
        val diameterAsRoot = leftBranchWithCurrentNode + rightBranchWithCurrentNode
        val maxDiameter = max(
            max(leftDiameter, rightDiameter),
            diameterAsRoot
        )
        return maxBranchLenWithCurrentNode to maxDiameter
    }
}