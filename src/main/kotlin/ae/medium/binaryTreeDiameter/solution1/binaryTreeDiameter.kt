package ae.medium.binaryTreeDiameter.solution1

import kotlin.math.max

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun binaryTreeDiameter(tree: BinaryTree?): Int {
    return getTreeInfo(tree).diameter
}

data class TreeInfo(var diameter: Int, var height: Int)

fun getTreeInfo(tree: BinaryTree?): TreeInfo {
    if (tree == null) {
        return TreeInfo(0, 0)
    }

    val leftTreeInfo = getTreeInfo(tree.left)
    val rightTreeInfo = getTreeInfo(tree.right)

    val longestPathThroughRoot = leftTreeInfo.height + rightTreeInfo.height
    val maxDiameterSoFar = max(leftTreeInfo.diameter, rightTreeInfo.diameter)
    val currentDiameter = max(longestPathThroughRoot, maxDiameterSoFar)
    val currentHeight = 1 + max(leftTreeInfo.height, rightTreeInfo.height)

    return TreeInfo(currentDiameter, currentHeight)
}