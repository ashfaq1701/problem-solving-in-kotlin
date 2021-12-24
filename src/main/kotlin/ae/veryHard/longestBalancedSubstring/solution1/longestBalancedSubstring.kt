package ae.veryHard.longestBalancedSubstring.solution1

import java.util.Stack
import kotlin.math.max

fun longestBalancedSubstring(string: String): Int {
    var maxLength = 0

    for (i in string.indices) {
        // Valid balanced substring should always have even length
        // Starting from length 2 "()" is sufficient.
        for (j in i + 2 .. string.length step 2) {
            if (isValid(string.substring(i, j))) {
                maxLength = max(maxLength, j - i)
            }
        }
    }
    return maxLength
}

fun isValid(string: String): Boolean {
    val stack = Stack<Char>()

    for (c in string) {
        if (c == '(') {
            stack.push(c)
        } else if (stack.isEmpty()) {
            return false
        } else {
            stack.pop()
        }
    }

    return stack.isEmpty()
}
