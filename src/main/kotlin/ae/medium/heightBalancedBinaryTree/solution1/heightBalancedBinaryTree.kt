package ae.medium.heightBalancedBinaryTree.solution1

import kotlin.math.abs
import kotlin.math.max

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun heightBalancedBinaryTree(tree: BinaryTree): Boolean {
    return getTreeInfo(tree).isBalanced
}

data class TreeInfo(var isBalanced: Boolean, var height: Int)

fun getTreeInfo(node: BinaryTree?): TreeInfo {
    node ?: return TreeInfo(true, -1)

    val leftSubtreeInfo = getTreeInfo(node.left)
    val rightSubtreeInfo = getTreeInfo(node.right)

    val isBalanced = leftSubtreeInfo.isBalanced && rightSubtreeInfo.isBalanced &&
            abs(leftSubtreeInfo.height - rightSubtreeInfo.height) <= 1
    val height = max(leftSubtreeInfo.height, rightSubtreeInfo.height) + 1
    return TreeInfo(isBalanced, height)
}
