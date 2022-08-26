package leetcode.stampingTheSequence.solution1

class Solution {
    fun movesToStamp(stamp: String, target: String): IntArray {
        val S = stamp.toCharArray()
        val T = target.toCharArray()

        val result = mutableListOf<Int>()
        val visited = MutableList<Boolean>(target.length) { false }
        var stars = 0

        while (stars < T.size) {
            var didReplace = false

            for (i in 0 .. T.size - S.size) {
                if (!visited[i] && canReplace(T, i, S)) {
                    stars = doReplace(T, i, S.size, stars)
                    didReplace = true
                    visited[i] = true
                    result.add(i)

                    if (stars == T.size) break
                }
            }

            if (!didReplace) {
                return intArrayOf()
            }
        }

        return result.reversed().toIntArray()

    }

    private fun canReplace(T: CharArray, start: Int, S: CharArray): Boolean {
        for (i in S.indices) {
            if (T[start + i] != '*' && T[start + i] != S[i]) {
                return false
            }
        }

        return true
    }

    private fun doReplace(T: CharArray, start: Int, stampLen: Int, stars: Int): Int {
        var currentStars = stars

        for (i in start until start + stampLen) {
            if (T[i] != '*') {
                T[i] = '*'
                currentStars += 1
            }
        }

        return currentStars
    }
}