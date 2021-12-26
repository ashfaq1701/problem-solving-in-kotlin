package ae.medium.bstTraversal.solution1

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun inOrderTraverse(tree: BST?, array: MutableList<Int>): List<Int> {
    tree ?: return array

    inOrderTraverse(tree.left, array)
    array.add(tree.value)
    inOrderTraverse(tree.right, array)
    return array
}

fun preOrderTraverse(tree: BST?, array: MutableList<Int>): List<Int> {
    tree ?: return array

    array.add(tree.value)
    preOrderTraverse(tree.left, array)
    preOrderTraverse(tree.right, array)
    return array
}

fun postOrderTraverse(tree: BST?, array: MutableList<Int>): List<Int> {
    tree ?: return array

    postOrderTraverse(tree.left, array)
    postOrderTraverse(tree.right, array)
    array.add(tree.value)
    return array
}
