package ae.easy.nodeDepths.solution2

import java.util.Stack

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun nodeDepths(root: BinaryTree): Int {
    val stk = Stack<Pair<BinaryTree?, Int>>()
    stk.push(root to 0)

    var sumOfDepths = 0

    while (stk.isNotEmpty()) {

        val (currentNode, currentDepth) = stk.pop()

        currentNode ?: continue

        sumOfDepths += currentDepth

        stk.push(currentNode.left to currentDepth + 1)
        stk.push(currentNode.right to currentDepth + 1)
    }

    return sumOfDepths
}