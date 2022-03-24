package ctci.shortestWordDistanceII.solution1

import kotlin.math.abs
import kotlin.math.min

class WordDistance(wordsDict: Array<String>) {

    private val positionMap = computeWordPositionMap(wordsDict)
    private val cache = mutableMapOf<String, MutableMap<String, Int>>()

    fun shortest(word1: String, word2: String): Int {
        val resultFromCache = getFromCache(word1, word2)

        if (resultFromCache != null) return resultFromCache

        val word1Indices = positionMap[word1]!!
        val word2Indices = positionMap[word2]!!

        var i = 0
        var j = 0

        var shortestDist = Int.MAX_VALUE
        while (i < word1Indices.size && j < word2Indices.size) {
            shortestDist = min(shortestDist, abs(word1Indices[i] - word2Indices[j]))

            if (word1Indices[i] < word2Indices[j]) {
                i += 1
            } else {
                j += 1
            }
        }

        storeInCache(word1, word2, shortestDist)
        storeInCache(word2, word1, shortestDist)

        return shortestDist
    }

    private fun computeWordPositionMap(words: Array<String>): Map<String, List<Int>> {
        val positionMap = mutableMapOf<String, MutableList<Int>>()

        for (idx in words.indices) {
            val word = words[idx]
            if (word !in positionMap) {
                positionMap[word] = mutableListOf()
            }

            positionMap[word]!!.add(idx)
        }

        return positionMap
    }

    private fun getFromCache(word1: String, word2: String): Int? {
        if (word1 in cache && word2 in cache[word1]!!) {
            return cache[word1]!![word2]!!
        }

        return null
    }

    private fun storeInCache(word1: String, word2: String, dist: Int) {
        if (word1 !in cache) {
            cache[word1] = mutableMapOf()
        }

        cache[word1]!![word2] = dist
    }

}