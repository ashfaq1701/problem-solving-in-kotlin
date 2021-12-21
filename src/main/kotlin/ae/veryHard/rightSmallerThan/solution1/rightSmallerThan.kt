package ae.veryHard.rightSmallerThan.solution1

// Construct a BST by inserting the input array's integers
// one by one, in reverse order (from right to left).
// At each insertion, once a new BST node is positioned in the BST,
// the number of nodes in its parent node's left subtree
// (plus the parent node itself, if its value is smaller than the inserted node's value)
// is the number of "right-smaller-than" elements for the element being inserted.
fun rightSmallerThan(array: List<Int>): List<Int> {
    if (array.isEmpty()) return listOf()

    val root = SpecialBST(array.lastIndex, array.last())

    for (i in array.lastIndex - 1 downTo 0) {
        root.insert(i, array[i])
    }

    val result = MutableList(array.size) { 0 }

    populateRightSmallerThan(root, result)

    return result
}

class SpecialBST(val idx: Int, val value: Int, val smallerCountInCreationTime: Int = 0) {
    var left: SpecialBST? = null
    var right: SpecialBST? = null

    var leftChildrenCount = 0

    fun insert(idx: Int, value: Int, smallerCountInCreationTime: Int = 0) {

        if (value < this.value) {
            leftChildrenCount += 1

            if (left == null) {
                left = SpecialBST(idx, value, smallerCountInCreationTime)
            } else {
                left!!.insert(idx, value, smallerCountInCreationTime)
            }
        } else {
            var nextSmallerCountInCreationTime = smallerCountInCreationTime
            nextSmallerCountInCreationTime += leftChildrenCount
            if (value > this.value) {
                nextSmallerCountInCreationTime += 1
            }

            if (right == null) {
                right = SpecialBST(idx, value, nextSmallerCountInCreationTime)
            } else {
                right!!.insert(idx, value, nextSmallerCountInCreationTime)
            }
        }
    }
}

fun populateRightSmallerThan(node: SpecialBST?, result: MutableList<Int>) {
    node ?: return

    result[node.idx] = node.smallerCountInCreationTime

    populateRightSmallerThan(node.left, result)
    populateRightSmallerThan(node.right, result)
}
