package ctci.shortestWordDistance.solution1

import kotlin.math.min

class Solution {
    fun shortestDistance(wordsDict: Array<String>, word1: String, word2: String): Int {
        var word1Idx: Int = -1
        var word2Idx: Int = -1

        var minDist = Int.MAX_VALUE

        for (idx in wordsDict.indices) {
            if (wordsDict[idx] == word1) {
                if (word2Idx != -1) {
                    minDist = min(minDist, idx - word2Idx)
                }

                word1Idx = idx
            } else if (wordsDict[idx] == word2) {
                if (word1Idx != -1) {
                    minDist = min(minDist, idx - word1Idx)
                }

                word2Idx = idx
            }
        }

        return minDist
    }
}