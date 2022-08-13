package leetcode.substringWithConcatenationOfAllWords.solution1

class Solution {

    private var wordCounts = mutableMapOf<String, Int>()
    private var countWords = 0
    private var singleWordLen = 0
    private var substrLen = 0

    fun findSubstring(s: String, words: Array<String>): List<Int> {
        countWords = words.size
        singleWordLen = words[0].length
        substrLen = singleWordLen * countWords

        for (word in words) {
            if (word !in wordCounts) {
                wordCounts[word] = 0
            }

            wordCounts[word] = wordCounts[word]!! + 1
        }

        val result = mutableListOf<Int>()

        for (i in 0 .. s.length - substrLen) {
            if (contains(i, s)) {
                result.add(i)
            }
        }

        return result
    }

    private fun contains(i: Int, s: String): Boolean {
        val copiedWordCounts = wordCounts.toMutableMap()

        for (j in i until i + substrLen step singleWordLen) {
            val potentialWord = s.substring(j, j + singleWordLen)

            if (potentialWord !in copiedWordCounts || copiedWordCounts[potentialWord]!! == 0) {
                return false
            }

            copiedWordCounts[potentialWord] = copiedWordCounts[potentialWord]!! - 1
        }

        return true
    }
}