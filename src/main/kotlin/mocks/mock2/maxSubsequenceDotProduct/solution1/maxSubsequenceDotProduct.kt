package mocks.mock2.maxSubsequenceDotProduct.solution1

fun maxSubsequenceDotProduct(arrayOne: List<Int>, arrayTwo: List<Int>): Int {
    val dp = MutableList(arrayOne.size + 1) { MutableList(arrayTwo.size + 1) { Int.MIN_VALUE } }

    for (r in 1 .. arrayOne.size) {
        for (c in 1 .. arrayTwo.size) {
            val currentProduct = arrayOne[r - 1] * arrayTwo[c - 1]

            val potentialValues = listOf(
                currentProduct,
                addAndHandleOverflow(dp[r - 1][c - 1], currentProduct),
                dp[r - 1][c - 1],
                dp[r][c - 1],
                dp[r - 1][c]
            )

            dp[r][c] = potentialValues.maxOrNull()!!
        }
    }

    return dp[arrayOne.size][arrayTwo.size]
}

fun addAndHandleOverflow(number: Int, toAdd: Int): Int {
    if (toAdd > 0) return number + toAdd

    val willOverflow = toAdd < 0 && number + toAdd > number

    return if (willOverflow) number else number + toAdd
}