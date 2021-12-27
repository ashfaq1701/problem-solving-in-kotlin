package ae.medium.balancedBrackets.solution1

import java.util.Stack

fun balancedBrackets(str: String): Boolean {

    val openingBrackets = "[{("
    val closingBrackets = "]})"

    val matchingBrackets = mapOf(
        ')' to '(',
        '}' to '{',
        ']' to '['
    )

    val stack = Stack<Char>()
    for (c in str) {
        if (openingBrackets.contains(c)) {
            stack.push(c)
        } else if (closingBrackets.contains(c)) {
            if (stack.isEmpty()) {
                return false
            } else if (matchingBrackets[c]!! == stack.peek()) {
                stack.pop()
            } else {
                return false
            }
        }
    }

    return stack.isEmpty()
}
