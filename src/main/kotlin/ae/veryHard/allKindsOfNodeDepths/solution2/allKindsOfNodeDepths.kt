package ae.veryHard.allKindsOfNodeDepths.solution2

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

// O(n) Time | O(n) Space
fun allKindsOfNodeDepths(root: BinaryTree): Int {
    val nodeCounts = mutableMapOf<BinaryTree, Int>()
    val nodeDepths = mutableMapOf<BinaryTree, Int>()

    populateNodeCounts(root, nodeCounts)
    populateNodeDepths(root, nodeDepths, nodeCounts)

    return getSumOfAllNodeDepths(root, nodeDepths)
}

fun populateNodeCounts(node: BinaryTree, nodeCounts: MutableMap<BinaryTree, Int>) {
    var count = 1

    if (node.left != null) {
        populateNodeCounts(node.left!!, nodeCounts)
        count += nodeCounts[node.left!!]!!
    }

    if (node.right != null) {
        populateNodeCounts(node.right!!, nodeCounts)
        count += nodeCounts[node.right!!]!!
    }

    nodeCounts[node] = count
}

fun populateNodeDepths(node: BinaryTree, nodeDepths: MutableMap<BinaryTree, Int>, nodeCounts: MutableMap<BinaryTree, Int>) {
    var depth = 0

    if (node.left != null) {
        populateNodeDepths(node.left!!, nodeDepths, nodeCounts)
        depth += (nodeDepths[node.left!!]!! + nodeCounts[node.left!!]!!)
    }

    if (node.right != null) {
        populateNodeDepths(node.right!!, nodeDepths, nodeCounts)
        depth += (nodeDepths[node.right!!]!! + nodeCounts[node.right!!]!!)
    }

    nodeDepths[node] = depth
}

fun getSumOfAllNodeDepths(node: BinaryTree?, nodeDepths: MutableMap<BinaryTree, Int>): Int {
    node ?: return 0

    return nodeDepths[node]!! + getSumOfAllNodeDepths(node.left, nodeDepths) + getSumOfAllNodeDepths(node.right, nodeDepths)
}
