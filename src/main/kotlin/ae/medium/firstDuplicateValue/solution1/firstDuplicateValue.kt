package ae.medium.firstDuplicateValue.solution1

fun firstDuplicateValue(array: MutableList<Int>): Int {
    val seen = mutableSetOf<Int>()

    for (i in array) {
        if (seen.contains(i)) {
            return i
        }

        seen.add(i)
    }

    return -1
}
