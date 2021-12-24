package ae.veryHard.longestBalancedSubstring.solution3

import kotlin.math.max

fun longestBalancedSubstring(string: String): Int {
    return max(
        getLongestBalancedInDirection(string, true),
        getLongestBalancedInDirection(string, false)
    )
}

fun getLongestBalancedInDirection(string: String, isLeftToRight: Boolean): Int {
    val openingParen = if (isLeftToRight) '(' else ')'

    var maxLen = 0
    var openingCount = 0
    var closingCount = 0

    val range = if (isLeftToRight) {
        string.indices
    } else {
        string.indices.reversed()
    }

    for (i in range) {
        val currentChar = string[i]

        if (currentChar == openingParen) {
            openingCount += 1
        } else {
            closingCount += 1
        }

        if (openingCount == closingCount) {
            maxLen = max(maxLen, openingCount * 2)
        } else if (closingCount > openingCount) {
            openingCount = 0
            closingCount = 0
        }
    }

    return maxLen
}