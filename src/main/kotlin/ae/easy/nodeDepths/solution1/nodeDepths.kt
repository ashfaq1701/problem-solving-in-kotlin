package ae.easy.nodeDepths.solution1

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun nodeDepths(root: BinaryTree): Int {
    return helper(root, 0)
}

fun helper(node: BinaryTree?, depth: Int): Int {
    node ?: return 0

    return depth + helper(node.left, depth + 1) + helper(node.right, depth + 1)
}