package mocks.mock2.largestBSTSize.solution1

import kotlin.math.max
import kotlin.math.min

fun largestBstSize(tree: BinaryTree): Int {
    val treeInfo = TreeInfo()
    getNodeInfo(tree, treeInfo)
    return treeInfo.longestBSTSize
}

fun getNodeInfo(tree: BinaryTree?, treeInfo: TreeInfo): NodeInfo {
    if (tree == null) return NodeInfo(true, 0, Int.MIN_VALUE, Int.MAX_VALUE)

    val leftNodeInfo = getNodeInfo(tree.left, treeInfo)
    val rightNodeInfo = getNodeInfo(tree.right, treeInfo)

    val isValidBST = leftNodeInfo.isValidBST &&
            rightNodeInfo.isValidBST &&
            tree.value > leftNodeInfo.maxValue &&
            tree.value <= rightNodeInfo.minValue

    val nodeCount = leftNodeInfo.nodeCount + rightNodeInfo.nodeCount + 1

    // OR
    //
    // val maxValue = max(tree.value, max(leftNodeInfo.maxValue, rightNodeInfo.maxValue))
    // val minValue = min(tree.value, min(leftNodeInfo.minValue, rightNodeInfo.minValue))
    //
    // But for me the below solution looks better. Because all nodes in right subtree is greater than or equal to the current node,
    // so max value will be coming from right subtree.
    // Similarly, all nodes in left subtree is smaller than the current node, so min value will be coming from the left subtree.
    val maxValue = max(rightNodeInfo.maxValue, tree.value)
    val minValue = min(leftNodeInfo.minValue, tree.value)

    if (isValidBST) {
        treeInfo.longestBSTSize = max(treeInfo.longestBSTSize, nodeCount)
    }

    return NodeInfo(isValidBST, nodeCount, maxValue, minValue)
}

// This is an input class. Do not edit.
open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

class TreeInfo {
    var longestBSTSize = 0
}

data class NodeInfo(val isValidBST: Boolean, val nodeCount: Int, val maxValue: Int, val minValue: Int)