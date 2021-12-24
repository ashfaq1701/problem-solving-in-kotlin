package ae.veryHard.smallestSubstringContaining.solution1

fun smallestSubstringContaining(bigString: String, smallString: String): String {
    val smallStringCounts = getCharacterCounts(smallString)
    val smallestSubstringBounds = getSmallestSubstrBounds(bigString, smallStringCounts)
    return getSubstringFromBounds(bigString, smallestSubstringBounds)
}

fun getCharacterCounts(string: String): Map<Char, Int> {
    return string.fold(mapOf()) { acc, c ->
        if (acc.containsKey(c)) {
            acc.plus(c to acc[c]!! + 1)
        } else {
            acc.plus(c to 1)
        }
    }
}

fun getSmallestSubstrBounds(bigStr: String, targetCharCounts: Map<Char, Int>): Pair<Int, Int> {
    // Initial bound. Biggest possible bound
    var substrBounds = mutableListOf(0, Integer.MAX_VALUE)
    val targetUniqueCharCount = targetCharCounts.size
    var currentUniqueCharCount = 0

    val currentCharCounts = mutableMapOf<Char, Int>()
    var leftIdx = 0
    var rightIdx = 0

    while (rightIdx < bigStr.length) {
        val rightChar = bigStr[rightIdx]

        // If target map doesn't contain this char, then ignore and move forward
        if (!targetCharCounts.containsKey(rightChar)) {
            rightIdx += 1
            continue
        }

        increaseCharacterCount(currentCharCounts, rightChar)
        if (currentCharCounts[rightChar] == targetCharCounts[rightChar]) {
            currentUniqueCharCount += 1
        }

        while (currentUniqueCharCount == targetUniqueCharCount && leftIdx <= rightIdx) {
            // Gap between 0 and INT_MAX is the biggest possible gap. So any substring will be less than that length
            substrBounds = getSmallerSubstrBounds(substrBounds[0], substrBounds[1], leftIdx, rightIdx)

            val leftChar = bigStr[leftIdx]

            // If target map doesn't contain this char, then this is useless character. ignore and move forward
            if (!targetCharCounts.containsKey(leftChar)) {
                leftIdx += 1
                continue
            }

            // aaa is less than aaaa. But we can't decrement currentUniqueCharCount each time we go down from aaa to aa to a etc.
            // This could happen if we did if (currentCharCounts[leftChar] < targetCharCounts[leftChar])
            // If we lost an essential character while sliding leftIdx then decrement the unique character count.
            if (currentCharCounts[leftChar] == targetCharCounts[leftChar]) {
                currentUniqueCharCount -= 1
            }

            // And for the above reason we decrease after checking current charCounts and decrementing currentUniqueCharCount based on that
            decreaseCharacterCount(currentCharCounts, leftChar)

            leftIdx += 1
        }

        rightIdx += 1
    }

    return substrBounds[0] to substrBounds[1]
}

fun getSubstringFromBounds(string: String, bounds: Pair<Int, Int>): String {
    if (bounds.second == Integer.MAX_VALUE) return ""
    return string.substring(bounds.first .. bounds.second)
}

fun increaseCharacterCount(charCounts: MutableMap<Char, Int>, char: Char) {
    if (charCounts.containsKey(char)) {
        charCounts[char] = charCounts[char]!! + 1
    } else {
        charCounts[char] = 1
    }
}

fun decreaseCharacterCount(charCounts: MutableMap<Char, Int>, char: Char) {
    charCounts[char] = charCounts[char]!! - 1
}

fun getSmallerSubstrBounds(l1: Int, r1: Int, l2: Int, r2: Int): MutableList<Int> {
    return if (r1 - l1 < r2 - l2) {
        mutableListOf(l1, r1)
    } else {
        mutableListOf(l2, r2)
    }
}