package ae.veryHard.flattenBinaryTree.solution1

open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun flattenBinaryTree(root: BinaryTree): BinaryTree {
    val inorderList = mutableListOf<BinaryTree>()

    populateInorderList(root, inorderList)

    for (i in 0 until inorderList.lastIndex) {
        val prevNode = inorderList[i]
        val nextNode = inorderList[i + 1]

        prevNode.right = nextNode
        nextNode.left = prevNode
    }

    return inorderList[0]
}

fun populateInorderList(node: BinaryTree?, inorderList: MutableList<BinaryTree>) {
    node ?: return

    populateInorderList(node.left, inorderList)
    inorderList.add(node)
    populateInorderList(node.right, inorderList)
}