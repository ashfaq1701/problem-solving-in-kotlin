package ae.medium.minHeightBST.solution3

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
    return constructMinHeightBST(array, 0, array.lastIndex)!!
}

fun constructMinHeightBST(array: List<Int>, startIdx: Int, endIdx: Int): BST? {
    if (endIdx < startIdx) return null

    val midIdx = (startIdx + endIdx) / 2
    val newBSTNode = BST(array[midIdx])

    newBSTNode.left = constructMinHeightBST(array, startIdx, midIdx - 1)
    newBSTNode.right = constructMinHeightBST(array, midIdx + 1, endIdx)

    return newBSTNode
}
