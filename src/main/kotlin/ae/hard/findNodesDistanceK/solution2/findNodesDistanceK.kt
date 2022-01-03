package ae.hard.findNodesDistanceK.solution2

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun findNodesDistanceK(tree: BinaryTree, target: Int, k: Int): List<Int> {
    val nodesAtDistanceK = mutableListOf<Int>()
    findDistanceFromNodeToTarget(tree, target, k, nodesAtDistanceK)
    return nodesAtDistanceK
}

fun findDistanceFromNodeToTarget(node: BinaryTree?, target: Int, k: Int, nodesAtDistanceK: MutableList<Int>): Int {
    if (node == null) return -1

    if (node.value == target) {
        findNodesAtDistanceK(node, 0, k, nodesAtDistanceK)
        return 1
    }

    val leftDistance = findDistanceFromNodeToTarget(node.left, target, k, nodesAtDistanceK)
    val rightDistance = findDistanceFromNodeToTarget(node.right, target, k, nodesAtDistanceK)

    if (leftDistance == k || rightDistance == k) {
        nodesAtDistanceK.add(node.value)
    }

    if (leftDistance != -1) {
        findNodesAtDistanceK(node.right, leftDistance + 1, k, nodesAtDistanceK)
        return leftDistance + 1
    }

    if (rightDistance != -1) {
        findNodesAtDistanceK(node.left, rightDistance + 1, k, nodesAtDistanceK)
        return rightDistance + 1
    }

    return -1
}

fun findNodesAtDistanceK(node: BinaryTree?, distance: Int, k: Int, nodesAtDistanceK: MutableList<Int>) {
    if (node == null) return

    if (distance == k) {
        nodesAtDistanceK.add(node.value)
    } else {
        findNodesAtDistanceK(node.left, distance + 1, k, nodesAtDistanceK)
        findNodesAtDistanceK(node.right, distance + 1, k, nodesAtDistanceK)
    }
}
