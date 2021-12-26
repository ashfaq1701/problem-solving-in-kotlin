package ae.medium.minHeightBST.solution2

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null

    fun insert(value: Int) {
        if (value < this.value) {
            if (this.left == null) {
                this.left = BST(value)
            } else {
                this.left!!.insert(value)
            }
        } else {
            if (this.right == null) {
                this.right = BST(value)
            } else {
                this.right!!.insert(value)
            }
        }
    }
}

fun minHeightBst(array: List<Int>): BST {
    return constructMinHeightBST(array, null, 0, array.lastIndex)!!
}

fun constructMinHeightBST(array: List<Int>, bst: BST?, startIdx: Int, endIdx: Int): BST? {
    if (endIdx < startIdx) return null

    val midIdx = (startIdx + endIdx) / 2
    val newBSTNode = BST(array[midIdx])

    if (bst != null) {
        if (array[midIdx] < bst.value) {
            bst.left = newBSTNode
        } else {
            bst.right = newBSTNode
        }
    }

    constructMinHeightBST(array, newBSTNode, startIdx, midIdx - 1)
    constructMinHeightBST(array, newBSTNode, midIdx + 1, endIdx)

    return newBSTNode
}
