package leetcode.binarySearchTreeIterator.solution2

import java.util.Stack

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class BSTIterator(root: TreeNode?) {

    val stack = Stack<TreeNode>()

    init {
        inOrderTraverse(root!!)
    }

    fun next(): Int {
        val topItem = stack.pop()

        if (topItem.right != null) {
            inOrderTraverse(topItem.right!!)
        }

        return topItem.`val`
    }

    fun hasNext(): Boolean {
        return stack.isNotEmpty()
    }

    fun inOrderTraverse(node: TreeNode) {
        stack.push(node)

        if (node.left != null) {
            inOrderTraverse(node.left!!)
        }
    }

}