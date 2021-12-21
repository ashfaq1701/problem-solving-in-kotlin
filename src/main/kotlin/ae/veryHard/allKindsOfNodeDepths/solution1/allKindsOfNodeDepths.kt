package ae.veryHard.allKindsOfNodeDepths.solution1

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

// O(n * log(n)) Time | O(h) Space
fun allKindsOfNodeDepths(root: BinaryTree): Int {
    val leftSumOfNodeDepths = root.left?.let { leftChild ->
        allKindsOfNodeDepths(leftChild)
    } ?: 0

    val rightSumOfNodeDepths = root.right?.let { rightChild ->
        allKindsOfNodeDepths(rightChild)
    } ?: 0

    return leftSumOfNodeDepths + rightSumOfNodeDepths + getNodeDepth(root)
}


fun getNodeDepth(node: BinaryTree?, depth: Int = 0): Int {
    node ?: return 0

    return depth + getNodeDepth(node.left, depth + 1) + getNodeDepth(node.right, depth + 1)
}
