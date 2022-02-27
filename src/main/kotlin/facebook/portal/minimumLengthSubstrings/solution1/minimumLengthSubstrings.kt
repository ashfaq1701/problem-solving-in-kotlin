package facebook.portal.minimumLengthSubstrings.solution1

import kotlin.math.min

fun main(args : Array<String>) {
    println(minLengthSubstring("dcbefebce", "fd"))
}

fun minLengthSubstring(s: String, t: String): Int {
    val smallStringCharCounts = getCharCounts(t)
    val bigStringCharCounts = mutableMapOf<Char, Int>()
    var lenOfWindow = Int.MAX_VALUE

    val targetCharCount = smallStringCharCounts.size
    var formedCharCount = 0

    var left = 0
    var right = 0

    while (right < s.length) {
        val rightChar = s[right]

        if (rightChar !in bigStringCharCounts) {
            bigStringCharCounts[rightChar] = 0
        }
        bigStringCharCounts[rightChar] = bigStringCharCounts[rightChar]!! + 1

        if (rightChar in smallStringCharCounts && bigStringCharCounts[rightChar] == smallStringCharCounts[rightChar]) {
            formedCharCount += 1
        }

        while (left <= right && formedCharCount == targetCharCount) {

            val leftChar = s[left]

            lenOfWindow = min(lenOfWindow, right - left + 1)

            if (leftChar in smallStringCharCounts && bigStringCharCounts[leftChar] == smallStringCharCounts[leftChar]) {
                formedCharCount -= 1
            }

            left += 1
        }

        right += 1
    }

    return lenOfWindow
}

fun getCharCounts(str: String): Map<Char, Int> {
    val charCounts = mutableMapOf<Char, Int>()

    for (ch in str) {
        if (ch !in charCounts) {
            charCounts[ch] = 0
        }

        charCounts[ch] = charCounts[ch]!! + 1
    }

    return charCounts
}