package ae.veryHard.rightSmallerThan.solution2

fun rightSmallerThan(array: List<Int>): List<Int> {
    if (array.isEmpty()) return listOf()

    val root = SpecialBST(array.lastIndex, array.last())

    val result = MutableList(array.size) { 0 }
    for (i in array.lastIndex - 1 downTo 0) {
        root.insert(i, array[i], result)
    }

    return result
}

class SpecialBST(val idx: Int, val value: Int) {
    var left: SpecialBST? = null
    var right: SpecialBST? = null

    var leftChildrenCount = 0

    fun insert(idx: Int, value: Int, result: MutableList<Int>, smallerCountInCreationTime: Int = 0) {

        if (value < this.value) {
            leftChildrenCount += 1

            if (left == null) {
                left = SpecialBST(idx, value)
                result[idx] = smallerCountInCreationTime
            } else {
                left!!.insert(idx, value, result, smallerCountInCreationTime)
            }
        } else {
            var nextSmallerCountInCreationTime = smallerCountInCreationTime
            nextSmallerCountInCreationTime += leftChildrenCount
            if (value > this.value) {
                nextSmallerCountInCreationTime += 1
            }

            if (right == null) {
                right = SpecialBST(idx, value)
                result[idx] = nextSmallerCountInCreationTime
            } else {
                right!!.insert(idx, value, result, nextSmallerCountInCreationTime)
            }
        }
    }
}
