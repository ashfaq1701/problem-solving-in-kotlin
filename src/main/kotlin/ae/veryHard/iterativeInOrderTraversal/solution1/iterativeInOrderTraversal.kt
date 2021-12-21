package ae.veryHard.iterativeInOrderTraversal.solution1

open class BinaryTree(value: Int, parent: BinaryTree?) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
    var parent: BinaryTree? = parent
}

fun iterativeInOrderTraversal(tree: BinaryTree?, callback: (BinaryTree?) -> Unit) {
    var currentNode = tree
    var prevNode: BinaryTree? = null

    while (currentNode != null) {

        val nextNode = when(prevNode) {
            null, currentNode.parent -> {
                if (currentNode.left != null) {
                    currentNode.left
                } else {
                    callback(currentNode)
                    if (currentNode.right != null) {
                        currentNode.right
                    } else {
                        currentNode.parent
                    }
                }
            }
            currentNode.left -> {
                callback(currentNode)
                if (currentNode.right != null) {
                    currentNode.right
                } else {
                    currentNode.parent
                }
            }
            else -> {
                currentNode.parent
            }
        }

        prevNode = currentNode
        currentNode = nextNode
    }
}
