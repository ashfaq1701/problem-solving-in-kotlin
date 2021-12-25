package ae.medium.threeNumberSum.solution1

fun threeNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    array.sort()

    for (i in 0 .. array.lastIndex) {
        val currentElement = array[i]
        val twoSumResults = twoSum(array, i + 1, array.lastIndex, targetSum - currentElement)

        for (twoSumResult in twoSumResults) {
            result.add(listOf(currentElement, twoSumResult[0], twoSumResult[1]))
        }
    }

    return result
}

fun twoSum(array: List<Int>, left: Int, right: Int, target: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    var leftIdx = left
    var rightIdx = right

    while (leftIdx < rightIdx) {
        val sum = array[leftIdx] + array[rightIdx]

        if (sum == target) {
            result.add(listOf(array[leftIdx], array[rightIdx]))
            leftIdx += 1
            rightIdx -= 1
        } else if (sum < target) {
            leftIdx += 1
        } else {
            rightIdx -= 1
        }
    }

    return result
}