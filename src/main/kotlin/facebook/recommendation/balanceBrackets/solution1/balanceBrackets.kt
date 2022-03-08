package facebook.recommendation.balanceBrackets.solution1

import java.util.Stack

fun main(args : Array<String>) {
    println(isBalanced("{[()]}"))
    println(isBalanced("{}()"))
    println(isBalanced("{(})"))
    println(isBalanced(")"))
}

fun isBalanced(args: String): Boolean {
    val matchedBracketsMap = mapOf(')' to '(', '}' to '{', ']' to '[')
    val openingBrackets = setOf('(', '{', '[')
    val closingBrackets = setOf(')', '}', ']')
    val stk = Stack<Char>()

    for (ch in args) {
        if (ch in openingBrackets) {
            stk.add(ch)
        } else if (ch in closingBrackets) {
            if (stk.isEmpty()) return false

            if (stk.pop() != matchedBracketsMap[ch]!!) return false
        }
    }

    return stk.isEmpty()
}