package ctci.validateBinarySearchTree.solution1

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun isValidBST(root: ctci.validateBinarySearchTree.solution2.TreeNode?): Boolean {
        return validationHelper(root, null, null)
    }

    fun validationHelper(node: ctci.validateBinarySearchTree.solution2.TreeNode?, minValue: ctci.validateBinarySearchTree.solution2.TreeNode?, maxValue: ctci.validateBinarySearchTree.solution2.TreeNode?): Boolean {
        if (node == null) return true

        if ((maxValue != null && node.`val` >= maxValue.`val`) || (minValue != null && node.`val` <= minValue.`val`)) {
            return false
        }

        return validationHelper(node.left, minValue, node) && validationHelper(node.right, node, maxValue)
    }
}
