package leetcode.substringWithConcatenationOfAllWords.solution2

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

        for (i in 0 until singleWordLen) {
            slidingWindowHelper(i, s, result)
        }

        return result
    }

    fun slidingWindowHelper(start: Int, s: String, result: MutableList<Int>) {
        var left = start
        var right = left
        val wordsFound = mutableMapOf<String, Int>()
        var wordsUsed = 0
        var excessWords = false

        while (right <= s.length - singleWordLen) {

            val sub = s.substring(right, right + singleWordLen)

            if (sub !in wordCounts) {
                wordsFound.clear()
                wordsUsed = 0
                excessWords = false
                left = right + singleWordLen
            } else {

                while (right - left == substrLen || excessWords) {
                    val leftMostWord = s.substring(left, left + singleWordLen)
                    left += singleWordLen

                    if (wordsFound[leftMostWord]!! > wordCounts[leftMostWord]!!) {
                        excessWords = false
                    } else {
                        wordsUsed -= 1
                    }

                    wordsFound[leftMostWord] = wordsFound[leftMostWord]!! - 1
                }

                if (sub !in wordsFound) {
                    wordsFound[sub] = 0
                }
                wordsFound[sub] = wordsFound[sub]!! + 1

                if (wordsFound[sub]!! <= wordCounts[sub]!!) {
                    wordsUsed += 1
                } else {
                    excessWords = true
                }

                if (wordsUsed == countWords && !excessWords) {
                    result.add(left)
                }
            }

            right += singleWordLen
        }
    }
}