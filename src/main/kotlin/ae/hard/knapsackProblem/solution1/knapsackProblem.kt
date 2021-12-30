package ae.hard.knapsackProblem.solution1

import kotlin.math.max

fun knapsackProblem(items: List<List<Int>>, capacity: Int): Pair<Int, List<Int>> {
    val knapsack = MutableList(items.size + 1) { MutableList(capacity + 1) { 0 } }

    for (i in 1 .. items.size) {
        val (currentValue, currentWeight) = items[i - 1]

        for (j in 0 .. capacity) {
            if (currentWeight > j) {
                knapsack[i][j] = knapsack[i - 1][j]
            } else {
                // knapsack[i - 1][j - currentWeight] + currentValue is current value added with
                // The value when we didn't have current weight added
                knapsack[i][j] = max(knapsack[i - 1][j], knapsack[i - 1][j - currentWeight] + currentValue)
            }
        }
    }

    return Pair(knapsack[items.size][capacity], buildSequence(knapsack, items))
}

fun buildSequence(knapsack: MutableList<MutableList<Int>>, items: List<List<Int>>): List<Int> {
    val result = mutableListOf<Int>()
    var r = knapsack.lastIndex
    var c = knapsack[0].lastIndex

    while (r > 0) {
        if (knapsack[r][c] == knapsack[r - 1][c]) {
            r -= 1
        } else {
            result.add(r - 1)

            val weight = items[r - 1][1]
            c -= weight

            r -= 1
        }

        if (c == 0) {
            break
        }
    }

    return result.reversed()
}
