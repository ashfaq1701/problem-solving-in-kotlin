package ae.medium.numberOfWaysToTraverseGraph.solution2

fun numberOfWaysToTraverseGraph(width: Int, height: Int): Int {
    val memo = MutableList(height + 1) { MutableList(width + 1) { -1 } }
    return recurse(width, height, memo)
}

fun recurse(width: Int, height: Int, memo: MutableList<MutableList<Int>>): Int {
    if (width == 1 || height == 1) return 1

    if (memo[height][width] != -1) return memo[height][width]

    memo[height][width] = recurse(width - 1, height, memo) + recurse(width, height - 1, memo)

    return memo[height][width]
}