package ae.hard.validateThreeNodes.solution1

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
    if (node == null) return false

    if (node.value == target.value) return true

    return if (target.value < node.value) {
        isDescendent(node.left, target)
    } else {
        isDescendent(node.right, target)
    }
}
