package ae.hard.longestSubstringWithoutDuplication.solution2

import kotlin.math.max

fun longestSubstringWithoutDuplication(string: String): String {
    val lastSeen = mutableMapOf<Char, Int>()
    var longest = 0 to 1
    var startIdx = 0

    for (i in string.indices) {
        val char = string[i]
        if (char in lastSeen) {
            startIdx = max(startIdx, lastSeen[char]!! + 1)
        }

        if (i + 1 - startIdx > longest.second - longest.first) {
            longest = startIdx to i + 1
        }

        lastSeen[char] = i
    }

    return string.substring(longest.first, longest.second)
}
