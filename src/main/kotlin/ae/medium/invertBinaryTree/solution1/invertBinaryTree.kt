package ae.medium.invertBinaryTree.solution1

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun invertBinaryTree(tree: BinaryTree) {
    invertBinaryTreeHelper(tree)
}

fun invertBinaryTreeHelper(tree: BinaryTree?) {
    tree ?: return

    swapLeftAndRight(tree)

    invertBinaryTreeHelper(tree.left)
    invertBinaryTreeHelper(tree.right)
}

fun swapLeftAndRight(tree: BinaryTree) {
    tree.left = tree.right.also {
        tree.right = tree.left
    }
}

