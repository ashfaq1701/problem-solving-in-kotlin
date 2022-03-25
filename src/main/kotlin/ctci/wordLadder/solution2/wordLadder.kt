package ctci.wordLadder.solution2

import java.util.LinkedList

class Solution {
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val wordGraph = prepareWordGraph(wordList)

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

            for (adjWord in getAdjacentWords(wordGraph, current)) {
                if (adjWord in visited) continue
                queue.add(adjWord to currentLen + 1)
            }
        }

        return 0
    }

    private fun prepareWordGraph(wordList: List<String>): Map<String, List<String>> {
        val wordGraph = mutableMapOf<String, MutableList<String>>()

        for (word in wordList) {
            for (pattern in getPatterns(word)) {
                if (pattern !in wordGraph) {
                    wordGraph[pattern] = mutableListOf()
                }
                wordGraph[pattern]!!.add(word)
            }
        }

        return wordGraph
    }

    private fun getAdjacentWords(wordGraph: Map<String, List<String>>, word: String): List<String> {
        val adjacentWords = mutableListOf<String>()

        for (pattern in getPatterns(word)) {
            wordGraph[pattern]?.let {
                adjacentWords.addAll(it)
            }
        }

        return adjacentWords
    }

    private fun getPatterns(word: String): List<String> {
        val patterns = mutableListOf<String>()

        for (idx in word.indices) {
            val pattern = word.substring(0, idx) + "*" + word.substring(idx + 1)
            patterns.add(pattern)
        }

        return patterns
    }
}