package ae.easy.twoNumberSum.solution1

fun twoNumberSum(array: MutableList<Int>, targetSum: Int): List<Int> {
    val numSet = mutableSetOf<Int>()

    array.forEach { currentNum ->
        if (numSet.contains(targetSum - currentNum)) {
            return listOf(currentNum, targetSum - currentNum)
        }

        numSet.add(currentNum)
    }

    return listOf()
}