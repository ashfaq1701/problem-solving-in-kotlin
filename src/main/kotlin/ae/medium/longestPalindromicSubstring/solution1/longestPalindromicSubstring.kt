package ae.medium.longestPalindromicSubstring.solution1

fun longestPalindromicSubstring(string: String): String {
    var currentLongest = listOf(0, 1)

    for (i in 1 .. string.lastIndex) {
        val odd = getLongestPalindromeFrom(string, i - 1, i + 1)
        val even = getLongestPalindromeFrom(string, i - 1, i)

        val longest = if (odd[1] - odd[0] > even[1] - even[0]) odd else even
        currentLongest = if (longest[1] - longest[0] > currentLongest[1] - currentLongest[0]) longest else currentLongest
    }

    return string.substring(currentLongest[0], currentLongest[1])
}


fun getLongestPalindromeFrom(string: String, leftIdx: Int, rightIdx: Int): List<Int> {
    var currentLeftIdx = leftIdx
    var currentRightIdx = rightIdx

    while (currentLeftIdx >= 0 && currentRightIdx < string.length) {
        if (string[currentLeftIdx] != string[currentRightIdx]) {
            break
        }

        currentLeftIdx -= 1
        currentRightIdx += 1
    }

    // At this point leftIdx and rightIdx are not part of palindrome.
    // Or they are out of bounds.
    // So the returned left index is inclusive but returned right index is exclusive.
    return listOf(currentLeftIdx + 1, currentRightIdx)
}