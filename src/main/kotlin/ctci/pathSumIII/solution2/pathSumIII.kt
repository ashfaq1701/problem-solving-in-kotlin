package ctci.pathSumIII.solution2

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        val sumCounts = mutableMapOf<Int, Int>()
        val treeInfo = TreeInfo()
        pathSumHelper(root, 0, sumCounts, treeInfo, targetSum)
        return treeInfo.pathSumCount
    }

    fun pathSumHelper(node: TreeNode?, prevSum: Int, sumCounts: MutableMap<Int, Int>, treeInfo: TreeInfo, targetSum: Int) {
        if (node == null) return

        val currentSum = prevSum + node.`val`

        if (currentSum == targetSum) {
            treeInfo.pathSumCount += 1
        }

        treeInfo.pathSumCount += sumCounts.getOrDefault(currentSum - targetSum, 0)

        if (currentSum !in sumCounts) {
            sumCounts[currentSum] = 0
        }
        sumCounts[currentSum] = sumCounts[currentSum]!! + 1

        pathSumHelper(node.left, currentSum, sumCounts, treeInfo, targetSum)
        pathSumHelper(node.right, currentSum, sumCounts, treeInfo, targetSum)

        sumCounts[currentSum] = sumCounts[currentSum]!! - 1
    }
}

class TreeInfo {
    var pathSumCount = 0
}