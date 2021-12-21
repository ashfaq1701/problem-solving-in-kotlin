package ae.veryHard.rightSiblingTree.solution1

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun rightSiblingTree(tree: BinaryTree): BinaryTree {
    mutate(tree, null, false)
    return tree
}

// There are two patterns:
// if a node is the left child of another node,
//   its right sibling is its parent's right child;
// if a node is the right child of another node,
//   its right sibling is its parent's right sibling's left child.
fun mutate(node: BinaryTree?, parent: BinaryTree?, isLeftChild: Boolean) {
    node ?: return

    val left = node.left
    val right = node.right

    // We transform left child first.
    mutate(left, node, true)

    // Then we transform the node.
    if (parent == null) {
        node.right = null
    } else if (isLeftChild) {
        // At this point parent->right
        // contains original right child of parent.
        // Because we transform left child before
        // transforming the parent itself.
        node.right = parent.right
    } else {
        // parent->right can be null when
        // parent->right was modified before to be null.
        // Because we transform parent before
        // transforming it's right child.
        // So parent's right will be overwritten
        // before we will reach to it's right child.
        if (parent.right == null) {
            node.right = null
        } else {
            // At this point parent->right
            // is the modified right pointer to it's right sibling.
            // So parent->right->left is the
            // left child of the parent's right sibling.
            node.right = parent.right!!.left
        }
    }

    // Then we transform the right child.
    mutate(right, node, false)
}