package leetcode.serializeAndDeserializeBinaryTree.solution1

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Codec() {
    // Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        val currentItems = mutableListOf<String>()
        serializeDFS(root, currentItems)
        println(currentItems.joinToString("->"))
        return currentItems.joinToString("->")
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        val items = data.split("->").reversed().toMutableList()
        return deserializeDFS(items)
    }

    private fun serializeDFS(node: TreeNode?, current: MutableList<String>) {
        if (node == null) {
            current.add("null")
            return
        }

        current.add(node.`val`.toString())

        serializeDFS(node.left, current)
        serializeDFS(node.right, current)
    }

    private fun deserializeDFS(items: MutableList<String>): TreeNode? {
        val latestItem = items.removeAt(items.lastIndex)

        if (latestItem == "null") {
            return null
        }

        val newNode = TreeNode(latestItem.toInt())
        newNode.left = deserializeDFS(items)
        newNode.right = deserializeDFS(items)
        return newNode
    }
}