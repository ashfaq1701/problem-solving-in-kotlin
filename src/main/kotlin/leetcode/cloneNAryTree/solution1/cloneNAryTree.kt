package leetcode.cloneNAryTree.solution1

class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}

class Solution {
    fun cloneTree(root: Node?): Node? {
        if (root == null) return null

        val clonedNode = Node(root.`val`)

        for (child in root.children) {
            clonedNode.children = clonedNode.children.plus(cloneTree(child))
        }

        return clonedNode
    }
}