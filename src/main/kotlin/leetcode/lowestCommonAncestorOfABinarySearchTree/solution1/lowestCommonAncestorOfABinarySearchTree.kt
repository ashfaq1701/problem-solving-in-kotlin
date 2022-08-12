package leetcode.lowestCommonAncestorOfABinarySearchTree.solution1

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        var currentNode = root

        while (!isOnDifferentSides(currentNode!!, p!!, q!!)) {
            currentNode = if (p.`val` < currentNode.`val`) {
                currentNode.left
            } else {
                currentNode.right
            }
        }

        return currentNode

    }

    private fun isOnDifferentSides(node: TreeNode, p: TreeNode, q: TreeNode): Boolean {
        return (p.`val` <= node.`val` && q.`val` >= node.`val`) ||
                (p.`val` >= node.`val` && q.`val` <= node.`val`)
    }
}