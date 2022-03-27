package leetcode.binaryTreePaths.solution1

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {

    private val paths = mutableListOf<String>()

    fun binaryTreePaths(root: TreeNode?): List<String> {
        if (root == null) return paths
        val current = mutableListOf<Int>()
        binaryTreePathsHelper(root, current)
        return paths
    }

    private fun binaryTreePathsHelper(node: TreeNode, current: MutableList<Int>) {
        current.add(node.`val`)

        if (node.left == null && node.right == null) {

            val path = current.map { it.toString() }.joinToString("->")
            paths.add(path)
        } else {

            if (node.left != null) {
                binaryTreePathsHelper(node.left!!, current)
            }

            if (node.right != null) {
                binaryTreePathsHelper(node.right!!, current)
            }
        }

        current.removeAt(current.lastIndex)
    }
}