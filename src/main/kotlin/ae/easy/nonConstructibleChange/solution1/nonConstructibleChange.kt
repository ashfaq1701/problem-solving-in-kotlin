package ae.easy.nonConstructibleChange.solution1

// Greedy
fun nonConstructibleChange(coins: MutableList<Int>): Int {
    // [1, 1, 2, 3, 5, 22] - 13
    coins.sort()

    var change = 0

    for (c in coins) {
        if (c > change + 1) {
            return change + 1
        }

        change += c
    }

    return change + 1
}

