package ae.veryHard.allKindsOfNodeDepths.solution3

/**
 * Formula:
 *
 * #n = #L + #R + 1
 * nD = nDL + #L + nDR + #R
 * aND = nD + aNDL + aNDR
 */

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

// O(n) Time | O(h) Space
fun allKindsOfNodeDepths(root: BinaryTree): Int {
    return getNodeInfo(root).allNodeDepths
}

data class NodeInfo(val nodeCount: Int, val nodeDepths: Int, val allNodeDepths: Int)

fun getNodeInfo(node: BinaryTree?): NodeInfo {
    node ?: return NodeInfo(0, 0, 0)

    val (leftNodeCount, leftNodeDepths, leftAllNodeDepths) = getNodeInfo(node.left)
    val (rightNodeCount, rightNodeDepths, rightAllNodeDepths) = getNodeInfo(node.right)

    val nodeCount = leftNodeCount + rightNodeCount + 1
    val nodeDepths = leftNodeDepths + leftNodeCount + rightNodeDepths + rightNodeCount
    val allNodeDepths = nodeDepths + leftAllNodeDepths + rightAllNodeDepths

    return NodeInfo(nodeCount, nodeDepths, allNodeDepths)
}
