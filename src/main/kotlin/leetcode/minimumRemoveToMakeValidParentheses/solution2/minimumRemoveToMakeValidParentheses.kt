package leetcode.minimumRemoveToMakeValidParentheses.solution2

class Solution {
    fun getValidParensInDirection(s: String, opening: Char, closing: Char): StringBuilder {
        val resultSB = StringBuilder()
        var balance = 0

        for (c in s) {
            if (c == opening) {
                balance += 1
            } else if (c == closing) {
                if (balance == 0) {
                    continue
                }

                balance -= 1
            }

            resultSB.append(c)
        }

        return resultSB
    }

    fun minRemoveToMakeValid(s: String): String {
        val removedClosing = getValidParensInDirection(s, '(', ')')
        val removedOpening = getValidParensInDirection(removedClosing.reverse().toString(), ')', '(')
        return removedOpening.reverse().toString()
    }
}