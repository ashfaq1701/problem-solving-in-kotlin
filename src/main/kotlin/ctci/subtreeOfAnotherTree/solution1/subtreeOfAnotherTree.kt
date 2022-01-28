package ctci.subtreeOfAnotherTree.solution1

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null) return root == subRoot

        if (subRoot == null) return true

        if (root.`val` == subRoot.`val` && inOrderCompare(root, subRoot)) {
            return true
        }

        return isSubtree(root.left, subRoot) ||  isSubtree(root.right, subRoot)
    }

    fun inOrderCompare(tree1: TreeNode?, tree2: TreeNode?): Boolean {
        if (tree1 == null || tree2 == null) return tree1 == tree2

        return inOrderCompare(tree1.left, tree2.left) &&
                (tree1.`val` == tree2.`val`) &&
                inOrderCompare(tree1.right, tree2.right)
    }
}