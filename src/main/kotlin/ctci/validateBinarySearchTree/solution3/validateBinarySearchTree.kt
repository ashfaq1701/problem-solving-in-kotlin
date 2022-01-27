package ctci.validateBinarySearchTree.solution3

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    var prev: TreeNode? = null

    // inorder traversal of a BST should produce sorted result
    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) return true

        if (!isValidBST(root.left)) return false

        if (prev != null && root.`val` <= prev!!.`val`) {
            return false
        }
        prev = root

        return isValidBST(root.right)
    }
}