package ctci.generateParentheses.solution1

class Solution {
    fun generateParenthesis(n: Int): List<String> {
        val results = mutableListOf<String>()
        val current = mutableListOf<Char>()
        generateParenthesisHelper(n, n, current, results)
        return results
    }

    fun generateParenthesisHelper(opening: Int, closing: Int, current: MutableList<Char>, results: MutableList<String>) {
        if (closing == 0) {
            results.add(String(current.toCharArray()))
            return
        }

        if (closing > opening) {
            current.add(')')
            generateParenthesisHelper(opening, closing - 1, current, results)
            current.removeAt(current.lastIndex)
        }

        if (opening > 0) {
            current.add('(')
            generateParenthesisHelper(opening - 1, closing, current, results)
            current.removeAt(current.lastIndex)
        }
    }
}