package ae.easy.twoNumberSum.solution2

fun twoNumberSum(array: MutableList<Int>, targetSum: Int): List<Int> {
    array.sort()

    var left = 0
    var right = array.lastIndex

    while (left < right) {
        val currentSum = array[left] + array[right]

        if (currentSum == targetSum) {
            return listOf(array[left], array[right])
        } else if (currentSum < targetSum) {
            left++
        } else {
            right--
        }
    }

    return listOf()
}