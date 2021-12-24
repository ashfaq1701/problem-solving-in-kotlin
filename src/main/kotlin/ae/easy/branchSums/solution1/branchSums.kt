package ae.easy.branchSums.solution1

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun branchSums(root: BinaryTree): List<Int> {
    val result = mutableListOf<Int>()
    dfsHelper(root, 0, result)
    return result
}

fun dfsHelper(node: BinaryTree, currentSum: Int, result: MutableList<Int>) {
    if (node.left == null && node.right == null) {
        result.add(currentSum + node.value)
        return
    }

    if (node.left != null) {
        dfsHelper(node.left!!, currentSum + node.value, result)
    }

    if (node.right != null) {
        dfsHelper(node.right!!, currentSum + node.value, result)
    }
}
