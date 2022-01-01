package ae.hard.longestSubstringWithoutDuplication.solution1

fun longestSubstringWithoutDuplication(string: String): String {
    var left = 0
    var right = 0

    var longestSubstr = 0 to 1

    val freq = mutableMapOf<Char, Int>()

    while (right < string.length) {
        val rightChar = string[right]

        if (!freq.containsKey(rightChar)) {
            freq[rightChar] = 0
        }
        freq[rightChar] = freq[rightChar]!! + 1

        while (freq[rightChar]!! > 1) {
            val leftChar = string[left]
            freq[leftChar] = freq[leftChar]!! - 1
            left += 1
        }

        if (right + 1 - left > longestSubstr.second - longestSubstr.first) {
            longestSubstr = left to right + 1
        }

        right += 1
    }

    return string.substring(longestSubstr.first, longestSubstr.second)
}
