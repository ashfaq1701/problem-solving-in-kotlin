package leetcode.uniquePaths.solution1

class Solution {
    fun uniquePaths(m: Int, n: Int): Int {
        val cache = List(m) { MutableList(n) { -1 } }
        return uniquePathsHelper(0, 0, m, n, cache)
    }

    private fun uniquePathsHelper(r: Int, c: Int, m: Int, n: Int, cache: List<MutableList<Int>>): Int {
        if (r == m - 1 && c == n - 1) {
            return 1
        }

        if (cache[r][c] != -1) {
            return cache[r][c]
        }

        cache[r][c] = if (r == m - 1) {
            uniquePathsHelper(r, c + 1, m, n, cache)
        } else if (c == n - 1) {
            uniquePathsHelper(r + 1, c, m, n, cache)
        } else {
            uniquePathsHelper(r + 1, c, m, n, cache) + uniquePathsHelper(r, c + 1, m, n, cache)
        }

        return cache[r][c]
    }
}