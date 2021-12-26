package ae.medium.reconstructBST.solution1

open class BST(value: Int, left: BST? = null, right: BST? = null) {
    var value = value
    var left = left
    var right = right
}

fun reconstructBst(preOrderTraversalValues: List<Int>): BST? {
    if (preOrderTraversalValues.isEmpty()) return null

    val currentValue = preOrderTraversalValues[0]
    var rightSubtreeRootIdx = preOrderTraversalValues.size

    for (i in 1 .. preOrderTraversalValues.lastIndex) {
        val value = preOrderTraversalValues[i]

        if (value >= currentValue) {
            rightSubtreeRootIdx = i
            break
        }
    }

    val leftSubtree = reconstructBst(preOrderTraversalValues.subList(1, rightSubtreeRootIdx))
    val rightSubtree = reconstructBst(preOrderTraversalValues.subList(rightSubtreeRootIdx, preOrderTraversalValues.size))

    val bst = BST(currentValue)
    bst.left = leftSubtree
    bst.right = rightSubtree

    return bst
}
