package facebook.recommendation.heightOfABinaryTree.solution1

import kotlin.math.max

class Node(val value: Int, val left: Node?, val right: Node?)

fun height(root: Node?): Int {
    if (root == null) return -1

    val leftHeight = height(root.left)
    val rightHeight = height(root.right)

    return max(leftHeight, rightHeight) + 1
}