package mocks.mock3.subtreesWithinRange.solution1

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun subtreesWithinRange(tree: BST, targetRange: List<Int>): Int {
    val treeInfo = TreeInfo()
    helper(tree, targetRange, treeInfo)
    return treeInfo.subtreeInRangeCount
}

fun helper(node: BST?, targetRange: List<Int>, treeInfo: TreeInfo): Boolean {
    if (node == null) return true

    val nodeInRange = node.value >= targetRange[0] && node.value <= targetRange[1]

    val leftSubtreeInRange = helper(node.left, targetRange, treeInfo)
    val rightSubtreeInRange = helper(node.right, targetRange, treeInfo)

    val subtreeInRange = nodeInRange && leftSubtreeInRange && rightSubtreeInRange

    if (subtreeInRange) {
        treeInfo.subtreeInRangeCount += 1
    }

    return subtreeInRange
}

class TreeInfo {
    var subtreeInRangeCount = 0
}