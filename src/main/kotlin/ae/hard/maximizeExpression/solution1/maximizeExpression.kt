package ae.hard.maximizeExpression.solution1

import kotlin.math.max

fun maximizeExpression(array: List<Int>): Int {
    if (array.size < 4) return 0

    var maxValue = Int.MIN_VALUE

    for (a in 0 .. array.lastIndex) {
        val aValue = array[a]

        for (b in a + 1 .. array.lastIndex) {
            val bValue = array[b]

            for (c in b + 1 .. array.lastIndex) {
                val cValue = array[c]

                for (d in c + 1 .. array.lastIndex) {
                    val dValue = array[d]

                    maxValue = max(maxValue, evaluateExpress(aValue, bValue, cValue, dValue))
                }
            }
        }
    }

    return maxValue
}

fun evaluateExpress(a: Int, b: Int, c: Int, d: Int): Int {
    return a - b + c - d
}
