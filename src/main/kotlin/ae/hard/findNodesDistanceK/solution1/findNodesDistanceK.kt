package ae.hard.findNodesDistanceK.solution1

import java.util.LinkedList

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun findNodesDistanceK(tree: BinaryTree, target: Int, k: Int): List<Int> {
    val nodesToParents = mutableMapOf<Int, BinaryTree?>()
    populateNodesToParents(tree, nodesToParents, null)
    val targetNode = getNodeFromValue(nodesToParents, tree, target)
    return bfsForNodesDistanceK(targetNode, nodesToParents, k)
}

fun populateNodesToParents(node: BinaryTree?, nodesToParents: MutableMap<Int, BinaryTree?>, parent: BinaryTree?) {
    if (node != null) {
        nodesToParents[node.value] = parent
        populateNodesToParents(node.left, nodesToParents, node)
        populateNodesToParents(node.right, nodesToParents, node)
    }
}

fun getNodeFromValue(nodesToParents: Map<Int, BinaryTree?>, tree: BinaryTree, target: Int): BinaryTree {
    // Handles root case
    if (tree.value == target) return tree

    val parent = nodesToParents[target]!!

    if (parent.left != null && parent.left!!.value == target) {
        return parent.left!!
    }

    return parent.right!!
}

fun bfsForNodesDistanceK(root: BinaryTree, nodesToParents: MutableMap<Int, BinaryTree?>, k: Int): List<Int> {
    val queue = LinkedList<Pair<BinaryTree, Int>>()
    // Visited is needed for below
    // Suppose we added a parent of a node. So if visited is not present
    // Then we can add the child back in the queue.
    val visited = mutableSetOf<Int>()

    queue.add(root to 0)

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        val (currentNode, currentDistance) = current

        visited.add(currentNode.value)

        // When we find one element at distance k
        // all remaining elements in the queue are at distance k.
        if (currentDistance == k) {
            val result = mutableListOf<Int>(currentNode.value)
            while (queue.isNotEmpty()) {
                val (node, _) = queue.poll()
                result.add(node.value)
            }
            return result
        }

        val possibleDests = listOf(currentNode.left, currentNode.right, nodesToParents[currentNode.value])
        for (nextNode in possibleDests) {
            if (nextNode == null) continue
            if (visited.contains(nextNode.value)) continue
            queue.add(nextNode to currentDistance + 1)
        }
    }

    return listOf()
}
