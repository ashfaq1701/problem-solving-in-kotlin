package ae.veryHard.longestBalancedSubstring.solution2

import kotlin.math.max

fun longestBalancedSubstring(string: String): Int {
    // We need this for the outer balanced substring
    // length calculation, such as "(()) or ()"
    // This extra value will indicate after which index
    // the balanced substring will start.
    val idxStack = mutableListOf(-1)
    var maxLength = 0

    for (i in string.indices) {
        if (string[i] == '(') {
            idxStack.add(i)
        } else {
            idxStack.last()
            idxStack.removeAt(idxStack.lastIndex)

            if (idxStack.isNotEmpty()) {
                val balancedSubstringStartingIdx = idxStack.last()
                val currentLen = i - balancedSubstringStartingIdx
                maxLength = max(maxLength, currentLen)
            } else {
                // This added index indicates to any NEXT balanced substring
                // That it starts after this index i, where the "unbalanced"
                // substring ended.
                idxStack.add(i)
            }
        }
    }

    return maxLength
}
