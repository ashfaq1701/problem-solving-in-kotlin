package ae.hard.longestCommonSubsequence.solution1

// O(NM * min(N, M))
fun longestCommonSubsequence(str1: String, str2: String): List<Char> {
    val dp = MutableList(str1.length + 1) {
        MutableList(str2.length + 1) { listOf<Char>() }
    }

    for (i in 1 .. str1.length) {
        for (j in 1 .. str2.length) {
            if (str1[i - 1] != str2[j - 1]) {
                dp[i][j] = if (dp[i - 1][j].size > dp[i][j - 1].size) {
                    dp[i - 1][j].toList()
                } else {
                    dp[i][j - 1].toList()
                }
            } else {
                dp[i][j] = dp[i - 1][j - 1].plus(str1[i - 1])
            }
        }
    }

    return dp[str1.length][str2.length]
}
