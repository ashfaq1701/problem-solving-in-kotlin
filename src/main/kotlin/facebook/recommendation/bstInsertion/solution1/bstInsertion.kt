package facebook.recommendation.bstInsertion.solution1

class Node(val data: Int, var left: Node? = null, var right: Node? = null)

fun bstInsert(root: Node?, data: Int): Node {
    val newNode = Node(data)

    var currentNode: Node? = root ?: return newNode

    while (currentNode != null) {
        if (data < currentNode.data) {
            if (currentNode.left != null) {
                currentNode = currentNode.left
            } else {
                currentNode.left = newNode
                break
            }
        } else {
            if (currentNode.right != null) {
                currentNode = currentNode.right
            } else {
                currentNode.right = newNode
                break
            }
        }
    }

    return root
}