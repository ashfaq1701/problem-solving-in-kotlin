package ae.easy.minimumWaitingTime.solution1

// [1, 2, 2, 3, 6]

// 0 + 1 + 1 + 2 + 1 + 2 + 2 + 1 + 2 + 2 + 3
// 1 -> 4
// 2 -> 3
// 2 -> 2
// 3 -> 1

// 0 + (1 * 4) = 4
// 4 + (2 * 3) = 10
// 10 + (2 * 2) = 14
// 14 + (3 * 1) = 17
// 17 + (6 * 0) = 17

fun minimumWaitingTime(queries: MutableList<Int>): Int {
    queries.sort()

    var totalWaitingTime = 0

    for (i in 0 .. queries.lastIndex) {
        val currentQuery = queries[i]
        val queriesLeft = queries.size - i - 1
        totalWaitingTime += (queriesLeft * currentQuery)
    }

    return totalWaitingTime
}
