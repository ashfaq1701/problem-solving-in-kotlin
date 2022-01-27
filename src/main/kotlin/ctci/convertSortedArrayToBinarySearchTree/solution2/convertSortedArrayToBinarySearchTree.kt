package ctci.convertSortedArrayToBinarySearchTree.solution2

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return createBST(nums, 0, nums.lastIndex)
    }

    fun createBST(nums: IntArray, left: Int, right: Int): TreeNode? {
        if (left > right) return null

        val mid = (left + right + 1) / 2
        val node = TreeNode(nums[mid])
        node.left = createBST(nums, left, mid - 1)
        node.right = createBST(nums, mid + 1, right)
        return node
    }
}