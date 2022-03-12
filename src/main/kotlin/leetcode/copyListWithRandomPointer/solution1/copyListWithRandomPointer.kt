package leetcode.copyListWithRandomPointer.solution1

class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null
}

class Solution {
    fun copyRandomList(node: Node?): Node? {
        val directory = mutableMapOf<Node, Node>()
        return copyRandomListHelper(node, directory)
    }

    fun copyRandomListHelper(node: Node?, directory: MutableMap<Node, Node>): Node? {
        if (node == null) return null

        if (node in directory) return directory[node]

        val copiedNode = Node(node.`val`)
        directory[node] = copiedNode

        copiedNode.next = copyRandomListHelper(node.next, directory)
        copiedNode.random = copyRandomListHelper(node.random, directory)

        return copiedNode
    }
}