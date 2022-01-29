package ctci.lowestCommonAncestorOfABinaryTree.solution1

import kotlin.math.max

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        val nodeToParents = mutableMapOf<TreeNode, TreeNode?>()
        populateNodeToParents(root, null, nodeToParents)

        val depthP = getDepth(root, p)
        val depthQ = getDepth(root, q)

        val (nodeOne, nodeTwo) = if (depthP > depthQ) {
            getKthParent(p, nodeToParents, depthP - depthQ) to q
        } else {
            p to getKthParent(q, nodeToParents, depthQ - depthP)
        }

        if (nodeOne == null || nodeTwo == null) return null

        return findCommonParent(nodeOne, nodeTwo, nodeToParents)
    }

    fun getDepth(root: TreeNode?, target: TreeNode?): Int {
        if (root == null) return -1

        if (root == target) return 0

        val depth = max(getDepth(root.left, target), getDepth(root.right, target))

        return if (depth == -1) return -1 else return depth + 1
    }

    fun populateNodeToParents(node: TreeNode?, parent: TreeNode?, nodeToParents: MutableMap<TreeNode, TreeNode?>) {
        if (node == null) return

        nodeToParents[node] = parent

        populateNodeToParents(node.left, node, nodeToParents)
        populateNodeToParents(node.right, node, nodeToParents)
    }

    fun getKthParent(node: TreeNode?, nodeToParents: MutableMap<TreeNode, TreeNode?>, k: Int): TreeNode? {
        if (node == null) return null

        var currentNode = node
        var currentDiff = k

        while (currentNode != null && currentDiff > 0) {
            currentNode = nodeToParents[currentNode]
            currentDiff -= 1
        }

        return currentNode
    }

    fun findCommonParent(nodeOne: TreeNode, nodeTwo: TreeNode, nodeToParents: MutableMap<TreeNode, TreeNode?>): TreeNode? {
        var currentNodeOne: TreeNode? = nodeOne
        var currentNodeTwo: TreeNode? = nodeTwo

        while (currentNodeOne != currentNodeTwo) {
            currentNodeOne = nodeToParents[currentNodeOne]
            currentNodeTwo = nodeToParents[currentNodeTwo]
        }

        return currentNodeOne
    }
}