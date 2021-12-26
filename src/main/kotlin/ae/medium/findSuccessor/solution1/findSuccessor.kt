package ae.medium.findSuccessor.solution1

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
    var parent: BinaryTree? = null
}

fun findSuccessor(tree: BinaryTree, node: BinaryTree): BinaryTree? {
    if (node.right != null) {
        return getLeftmostChild(node.right!!)
    }
    return getRightMostParent(node)
}

fun getLeftmostChild(node: BinaryTree): BinaryTree {
    var currentNode = node

    while (currentNode.left != null) {
        currentNode = currentNode.left!!
    }

    return currentNode
}

fun getRightMostParent(node: BinaryTree): BinaryTree? {
    var currentNode = node

    // We need the node for which our current node is in it's left subtree.
    // As long as current node is the right child of it's parent till then continue going up.
    while (currentNode.parent != null && currentNode.parent!!.right == currentNode) {
        currentNode = currentNode.parent!!
    }

    // At this moment current node is the left child of it's parent
    // OR it's the root node.
    // So return the parent of the current node.
    return currentNode.parent
}