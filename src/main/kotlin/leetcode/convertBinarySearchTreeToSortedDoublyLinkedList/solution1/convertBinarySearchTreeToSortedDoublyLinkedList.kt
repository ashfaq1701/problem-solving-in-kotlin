package leetcode.convertBinarySearchTreeToSortedDoublyLinkedList.solution1

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
}

class Solution {
    fun treeToDoublyList(root:Node?): Node? {
        if (root == null) return null

        val (left, right) = treeToDoublyListHelper(root)
        connectNodes(right, left)
        return left

    }

    fun treeToDoublyListHelper(node: Node): Pair<Node, Node> {
        val left = if (node.left == null) {
            node
        } else {
            val (leftOfLeft, rightOfLeft) = treeToDoublyListHelper(node.left!!)
            connectNodes(rightOfLeft, node)
            leftOfLeft
        }

        val right = if (node.right == null) {
            node
        } else {
            val (leftOfRight, rightOfRight) = treeToDoublyListHelper(node.right!!)
            connectNodes(node, leftOfRight)
            rightOfRight
        }

        return left to right
    }

    fun connectNodes(left: Node, right: Node) {
        left.right = right
        right.left = left
    }
}