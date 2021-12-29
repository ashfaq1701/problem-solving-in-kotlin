package ae.hard.maxPathSumInBinaryTree.solution1

import kotlin.math.max

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun maxPathSum(tree: BinaryTree): Int {
    return findMaxSum(tree).second
}

fun findMaxSum(node: BinaryTree?): Pair<Int, Int> {
    node ?: return 0 to Int.MIN_VALUE

    val (leftSubtreeBranchMaxSum, leftSubtreeMaxSum) = findMaxSum(node.left)
    val (rightSubtreeBranchMaxSum, rightSubtreeMaxSum) = findMaxSum(node.right)

    val value = node.value
    val childBranchMaxSum = max(leftSubtreeBranchMaxSum, rightSubtreeBranchMaxSum)
    val currentBranchMaxSum = max(childBranchMaxSum + value, value)
    val currentMaxSumAsRoot = max(leftSubtreeBranchMaxSum + value + rightSubtreeBranchMaxSum, currentBranchMaxSum)
    val currentMaxSum = max(
        max(leftSubtreeMaxSum, rightSubtreeMaxSum),
        currentMaxSumAsRoot
    )
    return currentBranchMaxSum to currentMaxSum
}