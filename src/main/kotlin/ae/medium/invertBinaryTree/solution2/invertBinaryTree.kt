package ae.medium.invertBinaryTree.solution2

import java.util.*


open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun invertBinaryTree(tree: BinaryTree) {
    val queue = LinkedList<BinaryTree>()
    queue.add(tree)

    while (queue.isNotEmpty()) {
        val current = queue.pollFirst()

        current.left = current.right.also {
            current.right = current.left
        }

        if (current.left != null) {
            queue.add(current.left!!)
        }

        if (current.right != null) {
            queue.add(current.right!!)
        }
    }
}
