package ctci.editDistance.solution1

class Solution {
    fun minDistance(word1: String, word2: String): Int {
        val cache = MutableList(word1.length + 1) { MutableList(word2.length + 1) { -1 } }
        return minDistanceHelper(word1, word2, 0, 0, cache)
    }

    fun minDistanceHelper(word1: String, word2: String, i: Int, j: Int, cache: MutableList<MutableList<Int>>): Int {
        if (i == word1.length && j == word2.length) {
            return  0
        }

        if (cache[i][j] != -1) {
            return cache[i][j]
        }

        cache[i][j] = when {
            i == word1.length -> 1 + minDistanceHelper(word1, word2, i, j + 1, cache)
            j == word2.length -> 1 + minDistanceHelper(word1, word2, i + 1, j, cache)
            word1[i] == word2[j] -> minDistanceHelper(word1, word2, i + 1, j + 1, cache)
            else -> 1 + listOf(
                minDistanceHelper(word1, word2, i + 1, j, cache),
                minDistanceHelper(word1, word2, i, j + 1, cache),
                minDistanceHelper(word1, word2, i + 1, j + 1, cache)
            ).minOrNull()!!
        }

        return cache[i][j]
    }
}