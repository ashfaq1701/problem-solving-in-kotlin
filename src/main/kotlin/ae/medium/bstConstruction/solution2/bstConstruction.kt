package ae.medium.bstConstruction.solution2

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null

    fun insert(value: Int): BST {
        var currentNode: BST? = this

        while (true) {
            if (value < currentNode!!.value) {
                if (currentNode.left == null) {
                    currentNode.left = BST(value)
                    break
                } else {
                    currentNode = currentNode.left
                }
            } else {
                if (currentNode.right == null) {
                    currentNode.right = BST(value)
                    break
                } else {
                    currentNode = currentNode.right
                }
            }
        }

        return this
    }

    fun contains(value: Int): Boolean {
        var currentNode: BST? = this

        while (currentNode != null) {
            if (value < currentNode.value) {
                currentNode = currentNode.left
            } else if (value > currentNode.value) {
                currentNode = currentNode.right
            } else {
                return true
            }
        }

        return false
    }

    fun remove(value: Int, parent: BST? = null): BST {
        var currentNode: BST? = this
        var parentNode = parent

        while (currentNode != null) {
            if (value < currentNode.value) {
                parentNode = currentNode
                currentNode = currentNode.left
            } else if (value > currentNode.value) {
                parentNode = currentNode
                currentNode = currentNode.right
            } else {
                if (currentNode.left != null && currentNode.right != null) {
                    currentNode.value = currentNode.right!!.getMinValue()
                    currentNode.right!!.remove(currentNode.value, currentNode)
                } else if (parentNode == null) {
                    if (currentNode.left != null) {
                        currentNode.value = currentNode.left!!.value
                        currentNode.right = currentNode.left!!.right
                        currentNode.left = currentNode.left!!.left
                    } else if (currentNode.right != null) {
                        currentNode.value = currentNode.right!!.value
                        currentNode.left = currentNode.right!!.left
                        currentNode.right = currentNode.right!!.right
                    } else {
                        // Node has no left child and no right child so how to remove it
                    }
                } else if (parentNode.left == currentNode) {
                    parentNode.left = if (currentNode.left != null) currentNode.left else currentNode.right
                } else if (parentNode.right == currentNode) {
                    parentNode.right = if (currentNode.left != null) currentNode.left else currentNode.right
                }

                break
            }
        }

        return this
    }

    fun getMinValue(): Int {
        var currentNode: BST? = this

        while (currentNode?.left != null) {
            currentNode = currentNode.left
        }

        return currentNode!!.value
    }
}