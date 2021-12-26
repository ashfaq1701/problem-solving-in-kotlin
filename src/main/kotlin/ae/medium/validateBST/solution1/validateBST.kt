package ae.medium.validateBST.solution1

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun validateBst(tree: BST): Boolean {
    return validate(tree, null, null)
}

fun validate(node: BST?, max: BST?, min: BST?): Boolean {
    node ?: return true

    if (max != null && node.value >= max.value) return false
    if (min != null && node.value < min.value) return false

    return validate(node.left, node, min) && validate(node.right, max, node)
}