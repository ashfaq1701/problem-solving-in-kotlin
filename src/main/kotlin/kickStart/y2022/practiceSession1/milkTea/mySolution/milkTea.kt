package kickStart.y2022.practiceSession1.milkTea.mySolution

import kotlin.math.min

fun parseAndFindMinComplaints() {
    val numTests = readLine()!!.toInt()

    for (i in 1 .. numTests) {
        val (n, m, p) = readLine()!!.split(" ").map { it.toInt() }
        val preferences = (0 until n).map { readLine()!! }
        val forbiddenTrie = Trie().also { trie ->
            (0 until m).forEach { _ ->
                val forbiddenCombination = readLine()!!
                trie.insert(forbiddenCombination)
            }
        }
        val minComplains = getMinComplains(preferences, forbiddenTrie, p, n)
        println("Case #$i: $minComplains")
    }
}

fun getMinComplains(preferences: List<String>, forbidden: Trie, p: Int, n: Int): Int {
    val prefCounts = MutableList(p) { 0 }

    for (preference in preferences) {
        for (j in preference.indices) {
            if (preference[j] == '1') {
                prefCounts[j] = prefCounts[j] + 1
            }
        }
    }

    return recurseCalculateMinComplains(0, prefCounts, forbidden, 0, MutableList(p) { '0' }, n)
}

fun recurseCalculateMinComplains(idx: Int, prefCounts: MutableList<Int>, forbidden: Trie, currentComplains: Int, current: MutableList<Char>, n: Int): Int {
    if (idx == prefCounts.size) {
        return if (forbidden contains String(current.toCharArray())) {
            Int.MAX_VALUE
        } else {
            currentComplains
        }
    }

    val currentPrefForOne = prefCounts[idx]
    val currentPrefForZero = n - prefCounts[idx]

    current[idx] = '1'
    val complainsChoosingOne = recurseCalculateMinComplains(idx + 1, prefCounts, forbidden, currentComplains + currentPrefForZero, current, n)

    current[idx] = '0'
    val complainsChoosingZero = recurseCalculateMinComplains(idx + 1, prefCounts, forbidden, currentComplains + currentPrefForOne, current, n)

    return min(complainsChoosingOne, complainsChoosingZero)
}

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
}

class Trie {

    val root = TrieNode()

    fun insert(str: String) {
        var current = root

        for (c in str) {
            if (c !in current.children) {
                current.children[c] = TrieNode()
            }

            current = current.children[c]!!
        }
    }

    infix fun contains(str: String): Boolean {
        var current = root

        for (c in str) {
            if (c !in current.children) {
                return false
            }

            current = current.children[c]!!
        }

        return true
    }
}

fun main() {
    parseAndFindMinComplaints()
}