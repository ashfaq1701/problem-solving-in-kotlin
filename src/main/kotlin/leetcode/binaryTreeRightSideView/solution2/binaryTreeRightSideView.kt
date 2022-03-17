package leetcode.binaryTreeRightSideView.solution2

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    val list = mutableListOf<Int>()

    fun rightSideView(root: TreeNode?): List<Int> {
        helper(root, 0)
        return list
    }

    fun helper(node: TreeNode?, level: Int) {
        if (node == null) return

        if (level == list.size) {
            list.add(node.`val`)
        }

        helper(node.right, level + 1)
        helper(node.left, level + 1)
    }
}