package ae.medium.numberOfWaysToTraverseGraph.solution1

fun numberOfWaysToTraverseGraph(width: Int, height: Int): Int {
    val memo = MutableList(height) { MutableList(width) { -1 } }
    return recurse(0, 0, height - 1, width - 1, memo)
}

fun recurse(r: Int, c: Int, bottom: Int, right: Int, memo: MutableList<MutableList<Int>>): Int {
    if (r == bottom || c == right) return 1

    if (memo[r][c] != -1) return memo[r][c]

    // Go down + go right
    memo[r][c] = recurse(r + 1, c, bottom, right, memo) + recurse(r, c + 1, bottom, right, memo)
    return memo[r][c]
}
