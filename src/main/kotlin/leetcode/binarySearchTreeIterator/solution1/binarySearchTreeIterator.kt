package leetcode.binarySearchTreeIterator.solution1

import java.util.Stack

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class BSTIterator(root: TreeNode?) {

    val stack = Stack<TreeNode>()

    init {
        reverseInOrderTraverse(root)
    }

    fun next(): Int {
        return stack.pop().`val`
    }

    fun hasNext(): Boolean {
        return stack.isNotEmpty()
    }

    fun reverseInOrderTraverse(node: TreeNode?) {
        if (node == null) return

        reverseInOrderTraverse(node.right)
        stack.push(node)
        reverseInOrderTraverse(node.left)
    }

}