package leetcode.wordLadderII.solution1

import java.util.LinkedList

class Solution {

    val adjList = mutableMapOf<String, MutableList<String>>()
    val currPath = mutableListOf<String>()
    val shortestPaths = mutableListOf<List<String>>()

    fun findLadders(beginWord: String, endWord: String, wordList: List<String>): List<List<String>> {
        val wordSet = HashSet<String>(wordList)
        bfs(beginWord, endWord, wordSet)
        currPath.add(endWord)
        backtrack(endWord, beginWord)
        return shortestPaths
    }

    private fun bfs(beginWord: String, endWord: String, wordSet: MutableSet<String>) {
        val queue = LinkedList<String>()
        queue.add(beginWord)

        if (beginWord in wordSet) {
            wordSet.remove(beginWord)
        }

        val isEnqueued = mutableSetOf<String>()
        isEnqueued.add(beginWord)

        while (queue.isNotEmpty()) {
            val visited = mutableSetOf<String>()

            val currentSize = queue.size

            for (i in 0 until currentSize) {
                val currentWord = queue.poll()

                val neighbors = getNeighbors(currentWord, wordSet)

                for (neighbor in neighbors) {
                    visited.add(neighbor)

                    if (neighbor !in adjList) {
                        adjList[neighbor] = mutableListOf<String>()
                    }
                    adjList[neighbor]!!.add(currentWord)

                    if (neighbor !in isEnqueued) {
                        queue.add(neighbor)
                        isEnqueued.add(neighbor)
                    }
                }
            }

            for (item in visited) {
                wordSet.remove(item)
            }
        }
    }

    private fun getNeighbors(word: String, wordSet: Set<String>): List<String> {
        val neighbors = mutableListOf<String>()
        val wordCharArray = word.toCharArray()

        for (i in wordCharArray.indices) {
            val oldChar = wordCharArray[i]

            for (ch in 'a' .. 'z') {
                wordCharArray[i] = ch
                val newWord = String(wordCharArray)
                if (newWord !in wordSet) continue
                neighbors.add(newWord)
            }

            wordCharArray[i] = oldChar
        }

        return neighbors
    }

    private fun backtrack(source: String, dest: String) {
        if (source == dest) {
            shortestPaths.add(currPath.reversed())
        }

        if (source !in adjList) return

        for (adjItem in adjList[source]!!) {
            currPath.add(adjItem)
            backtrack(adjItem, dest)
            currPath.removeAt(currPath.lastIndex)
        }
    }
}