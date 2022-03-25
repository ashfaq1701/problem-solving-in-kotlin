package ctci.wordLadder.solution1

import java.util.LinkedList

class Solution {
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val wordSet = wordList.toSet()

        val queue = LinkedList<Pair<String, Int>>()
        queue.add(beginWord to 0)

        val visited = mutableSetOf<String>()

        while (queue.isNotEmpty()) {
            val (current, currentLen) = queue.poll()

            if (current in visited) continue

            visited.add(current)
            if (current == endWord) {
                return currentLen + 1
            }

            for (adjWord in getAdjacentWords(current, wordSet)) {
                if (adjWord in visited) continue
                queue.add(adjWord to currentLen + 1)
            }
        }

        return 0
    }

    private fun getAdjacentWords(word: String, wordSet: Set<String>): List<String> {
        val adjacentWords = mutableListOf<String>()
        val charList = word.toMutableList()

        for (i in charList.indices) {
            val prevLetter = charList[i]

            for (letter in getLetters()) {
                if (letter != prevLetter) {
                    charList[i] = letter

                    val newWord = String(charList.toCharArray())
                    if (newWord in wordSet) {
                        adjacentWords.add(newWord)
                    }
                }

                charList[i] = prevLetter
            }
        }

        return adjacentWords
    }

    private fun getLetters() = (0 until 26).map { 'a' + it }
}