package ctci.inorderSuccessorInBSTII.solution1

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var parent: Node? = null
}

class Solution {
    fun inorderSuccessor(node: Node?): Node? {
        if (node == null) return null

        return if (node.right != null) {
            getLeftMostChild(node.right!!)
        } else {
            getRightMostParent(node)
        }
    }

    fun getLeftMostChild(node: Node): Node {
        var current = node

        while (current.left != null) {
            current = current.left!!
        }

        return current
    }

    fun getRightMostParent(node: Node): Node? {
        var current = node

        while (current.parent != null && current.parent!!.right == current) {
            current = current.parent!!
        }

        return current.parent
    }
}