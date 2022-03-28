package leetcode.alienDictionary.solution1

import java.util.LinkedList
import kotlin.math.min

class Solution {
    fun alienOrder(words: Array<String>): String {
        val jobGraph = prepareAdjacencyList(words)
        val sortedChars = topologicalSortGraph(jobGraph)
        return if (sortedChars.size == jobGraph.size) {
            String(sortedChars.toCharArray())
        } else ""
    }

    private fun topologicalSortGraph(graph: List<Node>): List<Char> {
        val sortedChars = mutableListOf<Char>()
        val nodesWithNoPrereqs = LinkedList(graph.filter { it.prereqCount == 0 })

        while (nodesWithNoPrereqs.isNotEmpty()) {
            val current = nodesWithNoPrereqs.poll()
            sortedChars.add(current.char)

            for (dep in current.deps) {
                dep.prereqCount -= 1
                if (dep.prereqCount == 0) {
                    nodesWithNoPrereqs.add(dep)
                }
            }
        }

        return sortedChars
    }

    private fun prepareAdjacencyList(words: Array<String>): List<Node> {
        val adjList = mutableMapOf<Char, Node>()

        for (word in words) {
            for (ch in word) {
                adjList[ch] = Node(ch)
            }
        }

        for (i in 0 until words.lastIndex) {
            val firstWord = words[i]
            val secondWord = words[i + 1]

            if (
                firstWord.length > secondWord.length &&
                firstWord.substring(0, secondWord.length) == secondWord
            ) {
                return mutableListOf()
            }

            for (j in 0 until min(firstWord.length, secondWord.length)) {
                if (firstWord[j] != secondWord[j]) {
                    val firstNode = adjList[firstWord[j]]!!
                    val depNode = adjList[secondWord[j]]!!
                    firstNode.deps.add(depNode)
                    depNode.prereqCount += 1
                    break
                }
            }
        }

        return adjList.values.toList()
    }
}

class Node(val char: Char, val deps: MutableList<Node> = mutableListOf(), var prereqCount: Int = 0)
