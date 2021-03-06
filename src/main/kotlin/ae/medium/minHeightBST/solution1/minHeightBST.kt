package ae.medium.minHeightBST.solution1

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

    var rootBst = bst
    val midIdx = (startIdx + endIdx) / 2
    val valueToAdd = array[midIdx]

    if (rootBst == null) {
        rootBst = BST(valueToAdd)
    } else {
        rootBst.insert(valueToAdd)
    }

    constructMinHeightBST(array, rootBst, startIdx, midIdx - 1)
    constructMinHeightBST(array, rootBst, midIdx + 1, endIdx)

    return rootBst
}
