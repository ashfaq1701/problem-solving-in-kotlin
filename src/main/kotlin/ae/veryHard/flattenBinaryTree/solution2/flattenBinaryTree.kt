package ae.veryHard.flattenBinaryTree.solution2

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun flattenBinaryTree(root: BinaryTree): BinaryTree {
    return flattenTree(root).first
}

fun flattenTree(node: BinaryTree): Pair<BinaryTree, BinaryTree> {
    val leftNode = if (node.left == null) {
        node
    } else {
        val (leftMostInLeftSubtree, rightMostInLeftSubtree) = flattenTree(node.left!!)
        connectNodes(rightMostInLeftSubtree, node)
        leftMostInLeftSubtree
    }

    val rightNode = if (node.right == null) {
        node
    } else {
        val (leftMostInRightSubtree, rightMostInRightSubtree) = flattenTree(node.right!!)
        connectNodes(node, leftMostInRightSubtree)
        rightMostInRightSubtree
    }

    return leftNode to rightNode
}

fun connectNodes(node1: BinaryTree, node2: BinaryTree) {
    node1.right = node2
    node2.left = node1
}
