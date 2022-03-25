package ctci.concatenatedWords.solution1

import kotlin.math.max

class Solution {

    fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
        val wordSet = words.toSet()
        return words.filter { findCompositionCount(0, it, wordSet, mutableMapOf()) > 1 }
    }

    fun findCompositionCount(startIdx: Int, word: String, wordSet: Set<String>, cache: MutableMap<Int, Int>): Int {
        if (startIdx == word.length) return 0

        if (startIdx in cache) return cache[startIdx]!!

        var maxComposition = -1

        for (idx in startIdx until word.length) {
            if (word.substring(startIdx .. idx) in wordSet) {
                val nextCompositions = findCompositionCount(idx + 1, word, wordSet, cache)
                if (nextCompositions != -1) {
                    maxComposition = max(maxComposition, 1 + nextCompositions)
                }
            }
        }

        cache[startIdx] = maxComposition
        return cache[startIdx]!!
    }
}