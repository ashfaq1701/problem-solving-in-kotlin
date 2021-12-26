package ae.medium.moveElementToEnd.solution1

fun moveElementToEnd(array: MutableList<Int>, toMove: Int): List<Int> {
    var slow = 0
    var fast = 0

    while (fast < array.size) {
        while (fast < array.size && array[fast] == toMove) {
            fast += 1
        }

        if (fast < array.size) {
            array[slow] = array[fast]
            slow += 1
            fast += 1
        }
    }

    for (i in slow .. array.lastIndex) {
        array[i] = toMove
    }

    return array
}
