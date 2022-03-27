package leetcode.regularExpressionMatching.solution1

/**
 * If Star,
 *
 * i, j + 2
 * i + 1, j (if current matches)
 *
 *
 * Else
 *
 * i + 1, j + 1 (if current matches)
 */

class Solution {
    private lateinit var cache: MutableList<MutableList<Int>>

    fun isMatch(s: String, p: String): Boolean {
        cache = MutableList(s.length + 1) { MutableList(p.length + 1) { -1 } }
        return isMatchHelper(s, p, 0, 0)
    }

    private fun isMatchHelper(s: String, p: String, i: Int, j: Int): Boolean {
        if (cache[i][j] != -1) {
            return cache[i][j] == 1
        }

        if (j == p.length) {
            cache[i][j] = if (i == s.length) 1 else 0
            return cache[i][j] == 1
        }

        val didCurrentMatch = i < s.length && (s[i] == p[j] || p[j] == '.')

        val isMatchFromIdx = if (j < p.lastIndex && p[j + 1] == '*') {
            isMatchHelper(s, p, i, j + 2) ||
                    (didCurrentMatch && isMatchHelper(s, p, i + 1, j))
        } else {
            didCurrentMatch && isMatchHelper(s, p, i + 1, j + 1)
        }

        cache[i][j] = if (isMatchFromIdx) 1 else 0
        return cache[i][j] == 1
    }
}