package leetcode.wordSubsets.solution1

import kotlin.math.max

class Solution {
    fun wordSubsets(words1: Array<String>, words2: Array<String>): List<String> {
        val words2Freq = getAllWordFreq(words2)
        return words1.filter { word ->
            verifyWord(word, words2Freq)
        }
    }

    private fun verifyWord(word: String, words2Freq: Map<Char, Int>): Boolean {
        val wordFreq = getWordFreq(word)

        for ((ch, count) in words2Freq) {
            if (ch !in wordFreq || wordFreq[ch]!! < count) {
                return false
            }
        }

        return true
    }

    private fun getAllWordFreq(words: Array<String>): Map<Char, Int> {
        val allWordFreq = mutableMapOf<Char, Int>()

        for (word in words) {
            val wordFreq = getWordFreq(word)
            for ((ch, freq) in wordFreq) {
                allWordFreq[ch] = max(allWordFreq[ch] ?: 0, freq)
            }
        }

        return allWordFreq
    }

    private fun getWordFreq(word: String): MutableMap<Char, Int> {
        val freq = mutableMapOf<Char, Int>()

        for (ch in word) {
            if (ch !in freq) {
                freq[ch] = 0
            }

            freq[ch] = freq[ch]!! + 1
        }

        return freq
    }
}