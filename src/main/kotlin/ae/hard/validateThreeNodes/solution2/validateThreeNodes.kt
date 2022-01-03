package ae.hard.validateThreeNodes.solution2

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun validateThreeNodes(nodeOne: BST, nodeTwo: BST, nodeThree: BST): Boolean {
    if (isDescendent(nodeOne, nodeTwo)) {
        return isDescendent(nodeTwo, nodeThree)
    }

    if (isDescendent(nodeThree, nodeTwo)) {
        return isDescendent(nodeTwo, nodeOne)
    }

    return false
}

fun isDescendent(node: BST?, target: BST): Boolean {
    var currentNode = node

    while (currentNode != null && currentNode != target) {
        currentNode = if (target.value < currentNode.value) {
            currentNode.left
        } else {
            currentNode.right
        }
    }

    return currentNode == target
}