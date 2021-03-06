package ctci.pathSumIII.solution1

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        val sumCounts = mapOf<Int, Int>()
        val treeInfo = TreeInfo()
        pathSumHelper(root, 0, sumCounts, treeInfo, targetSum)
        return treeInfo.pathSumCount
    }

    fun pathSumHelper(node: TreeNode?, prevSum: Int, sumCounts: Map<Int, Int>, treeInfo: TreeInfo, targetSum: Int) {
        if (node == null) return

        val currentSum = prevSum + node.`val`

        if (currentSum == targetSum) {
            treeInfo.pathSumCount += 1
        }

        treeInfo.pathSumCount += sumCounts.getOrDefault(currentSum - targetSum, 0)

        val newSumCounts = sumCounts.plus(currentSum to sumCounts.getOrDefault(currentSum, 0) + 1)

        pathSumHelper(node.left, currentSum, newSumCounts, treeInfo, targetSum)
        pathSumHelper(node.right, currentSum, newSumCounts, treeInfo, targetSum)
    }
}

class TreeInfo {
    var pathSumCount = 0
}