package ae.medium.validateBST.solution2

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun validateBst(tree: BST): Boolean {
    return validate(tree, Integer.MAX_VALUE, Integer.MIN_VALUE)
}

fun validate(node: BST?, maxValue: Int, minValue: Int): Boolean {
    node ?: return true

    if (node.value >= maxValue || node.value < minValue) return false

    return validate(node.left, node.value, minValue) && validate(node.right, maxValue, node.value)
}