package mocks.mock3.subtreesWithinRange.solution2

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun subtreesWithinRange(tree: BST, targetRange: List<Int>): Int {
    return getTreeInfo(tree, targetRange).numSubtreesWithinRange
}

fun getTreeInfo(tree: BST, targetRange: List<Int>): TreeInfo {
    var minValue = tree.value
    var maxValue = tree.value
    var numSubtreesWithinRange = 0

    if (tree.left != null) {
        val leftTreeInfo = getTreeInfo(tree.left!!, targetRange)
        minValue = leftTreeInfo.minValue
        numSubtreesWithinRange += leftTreeInfo.numSubtreesWithinRange
    }

    if (tree.right != null) {
        val rightTreeInfo = getTreeInfo(tree.right!!, targetRange)
        maxValue = rightTreeInfo.maxValue
        numSubtreesWithinRange += rightTreeInfo.numSubtreesWithinRange
    }

    if (minValue >= targetRange[0] && maxValue <= targetRange[1]) {
        numSubtreesWithinRange += 1
    }

    return TreeInfo(minValue, maxValue, numSubtreesWithinRange)
}

class TreeInfo(val minValue: Int, val maxValue: Int, val numSubtreesWithinRange: Int)
