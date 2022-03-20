package leetcode.removeInvalidParentheses.solution1

class Solution {
    private var minParensRemoved = Int.MAX_VALUE
    private val resultsWithMinParensRemoved = HashSet<String>()
    private val current = mutableListOf<Char>()

    fun removeInvalidParentheses(s: String): List<String> {
        helper(s, 0, 0, 0, 0)
        return resultsWithMinParensRemoved.toList()
    }

    private fun helper(s: String, idx: Int, openingCount: Int, closingCount: Int, removedCount: Int) {
        if (idx == s.length) {
            if (closingCount == openingCount) {
                if (removedCount < minParensRemoved) {
                    minParensRemoved = removedCount
                    resultsWithMinParensRemoved.clear()
                }

                if (removedCount == minParensRemoved) {
                    resultsWithMinParensRemoved.add(String(current.toCharArray()))
                }
            }
            return
        }

        val ch = s[idx]
        if (ch != '(' && ch != ')') {
            current.add(ch)
            helper(s, idx + 1, openingCount, closingCount, removedCount)
            current.removeAt(current.lastIndex)
        } else {
            helper(s, idx + 1, openingCount, closingCount, removedCount + 1)

            current.add(ch)
            if (ch == '(') {
                helper(s, idx + 1, openingCount + 1, closingCount, removedCount)
            } else if (openingCount > closingCount) {
                helper(s, idx + 1, openingCount, closingCount + 1, removedCount)
            }
            current.removeAt(current.lastIndex)
        }
    }
}