package ae.medium.numberOfWaysToTraverseGraph.solution3

fun numberOfWaysToTraverseGraph(width: Int, height: Int): Int {
    val dp = MutableList(height) { MutableList(width) { 0 } }

    for (r in 0 until height) {
        for (c in 0 until width) {
            if (r == 0 || c == 0) {
                dp[r][c] = 1
            } else {
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1]
            }
        }
    }

    return dp[height - 1][width - 1]
}
