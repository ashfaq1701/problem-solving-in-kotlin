package ae.hard.sameBSTs.solution1

fun sameBsts(arrayOne: List<Int>, arrayTwo: List<Int>): Boolean {
    if (arrayOne.size != arrayTwo.size) return false

    if (arrayOne.isEmpty() && arrayTwo.isEmpty()) return true

    if (arrayOne[0] != arrayTwo[0]) return false

    val leftOne = getSmaller(arrayOne)
    val rightOne = getGreaterThanOrEqualTo(arrayOne)

    val leftTwo = getSmaller(arrayTwo)
    val rightTwo = getGreaterThanOrEqualTo(arrayTwo)

    return sameBsts(leftOne, leftTwo) && sameBsts(rightOne, rightTwo)
}

fun getSmaller(array: List<Int>): List<Int> {
    val rootValue = array[0]
    return array.subList(1, array.size).filter { it < rootValue }
}

fun getGreaterThanOrEqualTo(array: List<Int>): List<Int> {
    val rootValue = array[0]
    return array.subList(1, array.size).filter { it >= rootValue }
}

