package ae.medium.findKthLargestValueInBST.solution1

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun findKthLargestValueInBst(tree: BST, k: Int): Int {
    val treeInfo = TreeInfo(0, -1)
    reverseInorderTraverse(tree, k, treeInfo)
    return treeInfo.latestVisitedNodeValue
}

data class TreeInfo(var numberOfNodesVisited: Int, var latestVisitedNodeValue: Int)

fun reverseInorderTraverse(node: BST?, k: Int, treeInfo: TreeInfo) {
    if (node == null || treeInfo.numberOfNodesVisited >= k) return

    reverseInorderTraverse(node.right, k, treeInfo)

    if (treeInfo.numberOfNodesVisited < k) {
        treeInfo.numberOfNodesVisited += 1
        treeInfo.latestVisitedNodeValue = node.value

        reverseInorderTraverse(node.left, k, treeInfo)
    }
}