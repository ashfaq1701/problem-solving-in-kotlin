package ctci.lowestCommonAncestorOfABinaryTree.solution2

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        return getCommonParentForNodes(root, p, q).first
    }

    fun getCommonParentForNodes(node: TreeNode?, p: TreeNode?, q: TreeNode?): Pair<TreeNode?, Int> {
        if (node == null) {
            return null to 0
        }

        val (leftCommonParent, leftCountImportantNodes) = getCommonParentForNodes(node.left, p, q)
        val (rightCommonParent, rightCountImportantNodes) = getCommonParentForNodes(node.right, p, q)

        if (leftCommonParent != null || rightCommonParent != null) {
            return if (leftCommonParent != null) {
                leftCommonParent to leftCountImportantNodes
            } else {
                rightCommonParent to rightCountImportantNodes
            }
        }

        var countOfImportantNodes = leftCountImportantNodes + rightCountImportantNodes

        if (node == p || node == q) {
            countOfImportantNodes += 1
        }

        val commonParentNode = if (countOfImportantNodes == 2) node else null

        return commonParentNode to countOfImportantNodes
    }
}