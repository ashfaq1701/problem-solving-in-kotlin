package facebook.portal.slowSums.solution1

fun main(args : Array<String>) {
    println(getTotalTime(arrayOf(4, 2, 1, 3)))
}

fun getTotalTime(arr: Array<Int>): Int {
    val sortedArr = arr.sorted()

    var currentSum = sortedArr.last()
    var penalty = 0

    for (i in sortedArr.lastIndex - 1 downTo 0) {
        currentSum += sortedArr[i]
        penalty += currentSum
    }

    return penalty
}