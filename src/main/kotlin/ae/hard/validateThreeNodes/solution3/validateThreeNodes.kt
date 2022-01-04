package ae.hard.validateThreeNodes.solution3

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun validateThreeNodes(nodeOne: BST, nodeTwo: BST, nodeThree: BST): Boolean {
    var searchOne: BST? = nodeOne
    var searchTwo: BST? = nodeThree

    while (true) {
        val findNodeThreeFromNodeOne = searchOne == nodeThree
        val findNodeOneFromNodeThree = searchTwo == nodeOne
        val foundNodeTwo = searchOne == nodeTwo || searchTwo == nodeTwo
        val finishedSearching = searchOne == null && searchTwo == null

        if (findNodeThreeFromNodeOne || findNodeOneFromNodeThree || foundNodeTwo || finishedSearching) {
            break
        }

        if (searchOne != null) {
            searchOne = if (nodeTwo.value < searchOne.value) searchOne.left else searchOne.right
        }

        if (searchTwo != null) {
            searchTwo = if (nodeTwo.value < searchTwo.value) searchTwo.left else searchTwo.right
        }
    }

    val foundEachOther = searchOne == nodeThree || searchTwo == nodeOne
    val foundNodeTwo = searchOne == nodeTwo || searchTwo == nodeTwo

    if (!foundNodeTwo || foundEachOther) return false

    return searchForNode(
        nodeTwo,
        if (searchOne == nodeTwo) nodeThree else nodeOne
    )
}

fun searchForNode(node: BST, target: BST): Boolean {
    var currentNode: BST? = node

    while (currentNode != null && currentNode != target) {
        currentNode = if (target.value < currentNode.value) {
            currentNode.left
        } else {
            currentNode.right
        }
    }

    return currentNode == target
}