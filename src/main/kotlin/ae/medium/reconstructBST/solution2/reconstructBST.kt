package ae.medium.reconstructBST.solution2

open class BST(value: Int, left: BST? = null, right: BST? = null) {
    var value = value
    var left = left
    var right = right
}

fun reconstructBst(preOrderTraversalValues: List<Int>): BST? {
    val treeInfo = TreeInfo(0)
    return reconstructBstFromRange(Integer.MIN_VALUE, Integer.MAX_VALUE, preOrderTraversalValues, treeInfo)!!
}

data class TreeInfo(var rootIdx: Int)

fun reconstructBstFromRange(lowerBound: Int, upperBound: Int, preOrderTraversalValues: List<Int>, currentSubtreeInfo: TreeInfo): BST? {
    if (currentSubtreeInfo.rootIdx == preOrderTraversalValues.size) {
        return null
    }

    val rootValue = preOrderTraversalValues[currentSubtreeInfo.rootIdx]

    if (rootValue < lowerBound || rootValue >= upperBound) {
        return null
    }

    currentSubtreeInfo.rootIdx += 1

    val leftSubtree = reconstructBstFromRange(lowerBound, rootValue, preOrderTraversalValues, currentSubtreeInfo)
    val rightSubtree = reconstructBstFromRange(rootValue, upperBound, preOrderTraversalValues, currentSubtreeInfo)

    val bst = BST(rootValue)
    bst.left = leftSubtree
    bst.right = rightSubtree

    return bst
}
