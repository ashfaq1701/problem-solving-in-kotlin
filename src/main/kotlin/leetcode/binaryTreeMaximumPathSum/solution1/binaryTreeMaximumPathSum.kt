package leetcode.binaryTreeMaximumPathSum.solution1

import kotlin.math.max

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun maxPathSum(root: TreeNode?): Int {
        return maxPathSumHelper(root).second
    }

    private fun maxPathSumHelper(node: TreeNode?): Pair<Int, Int> {
        if (node == null) return 0 to Int.MIN_VALUE

        val (leftBranchSum, leftPathSum) = maxPathSumHelper(node.left)
        val (rightBranchSum, rightPathSum) = maxPathSumHelper(node.right)

        val maxBranchSum = max(leftBranchSum, rightBranchSum)
        val maxBranchSumWithCurrentNode = max(maxBranchSum + node.`val`, node.`val`)
        val maxPathSumAsRoot = max(leftBranchSum + node.`val` + rightBranchSum, maxBranchSumWithCurrentNode)
        val maxPathSum = max(
            max(leftPathSum, rightPathSum),
            maxPathSumAsRoot
        )
        return maxBranchSumWithCurrentNode to maxPathSum
    }
}