package ctci.validateBinarySearchTree.solution2

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun isValidBST(root: ctci.validateBinarySearchTree.solution1.TreeNode?): Boolean {
        return validationHelper(root, null, null)
    }

    fun validationHelper(node: ctci.validateBinarySearchTree.solution1.TreeNode?, minValue: ctci.validateBinarySearchTree.solution1.TreeNode?, maxValue: ctci.validateBinarySearchTree.solution1.TreeNode?): Boolean {
        if (node == null) return true

        val isCurrentNodeValid = (maxValue == null || node.`val` < maxValue.`val`) && (minValue == null || node.`val` > minValue.`val`)

        val isLeftSubtreeValid = validationHelper(node.left, minValue, node)
        val isRightSubtreeValid = validationHelper(node.right, node, maxValue)

        return isCurrentNodeValid && isLeftSubtreeValid && isRightSubtreeValid
    }
}