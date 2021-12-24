package ae.easy.findThreeLargestNumbers.solution1

fun findThreeLargestNumbers(array: List<Int>): List<Int> {
    val threeLargest = MutableList<Int>(3) { Integer.MIN_VALUE }

    for (n in array) {
        updateLargest(threeLargest, n)
    }

    return threeLargest
}

fun updateLargest(threeLargest: MutableList<Int>, num: Int) {
    if (threeLargest[2] == Integer.MIN_VALUE || num > threeLargest[2]) {
        shiftAndUpdate(threeLargest, num, 2)
    } else if (threeLargest[1] == Integer.MIN_VALUE || num > threeLargest[1]) {
        shiftAndUpdate(threeLargest, num, 1)
    } else if (threeLargest[0] == Integer.MIN_VALUE || num > threeLargest[0]) {
        shiftAndUpdate(threeLargest, num, 0)
    }
}

fun shiftAndUpdate(threeLargest: MutableList<Int>, num: Int, idx: Int) {
    for (i in 0 .. idx) {
        if (i == idx) {
            threeLargest[i] = num
        } else {
            threeLargest[i] = threeLargest[i + 1]
        }
    }
}
