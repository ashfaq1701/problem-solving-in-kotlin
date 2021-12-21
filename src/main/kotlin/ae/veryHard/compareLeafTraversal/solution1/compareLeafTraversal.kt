package ae.veryHard.compareLeafTraversal.solution1

import java.util.*

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun compareLeafTraversal(tree1: BinaryTree, tree2: BinaryTree): Boolean {
    val tree1Stack = Stack<BinaryTree>()
    val tree2Stack = Stack<BinaryTree>()

    tree1Stack.push(tree1)
    tree2Stack.push(tree2)

    while (tree1Stack.isNotEmpty() && tree2Stack.isNotEmpty()) {
        val leftNode1 = getNextLeafNode(tree1Stack)
        val leafNode2 = getNextLeafNode(tree2Stack)

        if (leftNode1.value != leafNode2.value) {
            return false
        }
    }

    return tree1Stack.isEmpty() && tree2Stack.isEmpty()
}

fun getNextLeafNode(stack: Stack<BinaryTree>): BinaryTree {
    var currentNode = stack.pop()

    while (!isLeafNode(currentNode)) {

        currentNode.left?.let { leftChild ->
            stack.push(leftChild)
        }

        currentNode.right?.let { rightChild ->
            stack.push(rightChild)
        }

        currentNode = stack.pop()
    }

    return currentNode
}

fun isLeafNode(node: BinaryTree): Boolean = node.left == null && node.right == null