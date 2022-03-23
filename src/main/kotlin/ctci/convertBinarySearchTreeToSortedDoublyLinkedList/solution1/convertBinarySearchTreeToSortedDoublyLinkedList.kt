package ctci.convertBinarySearchTreeToSortedDoublyLinkedList.solution1

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
}

class Solution {
    fun treeToDoublyList(root:Node?): Node? {
        if (root == null) return null
        val (leftNode, rightNode) = treeToDoublyListHelper(root)
        connectNodes(rightNode, leftNode)
        return leftNode
    }

    private fun treeToDoublyListHelper(node: Node): Pair<Node, Node> {
        val leftNode = if (node.left == null) {
            node
        } else {
            val (leftOfLeftNode, rightOfLeftNode) = treeToDoublyListHelper(node.left!!)
            connectNodes(rightOfLeftNode, node)
            leftOfLeftNode
        }

        val rightNode = if (node.right == null) {
            node
        } else {
            val (leftOfRightNode, rightOfRightNode) = treeToDoublyListHelper(node.right!!)
            connectNodes(node, leftOfRightNode)
            rightOfRightNode
        }

        return leftNode to rightNode
    }

    private fun connectNodes(left: Node, right: Node) {
        left.right = right
        right.left = left
    }
}