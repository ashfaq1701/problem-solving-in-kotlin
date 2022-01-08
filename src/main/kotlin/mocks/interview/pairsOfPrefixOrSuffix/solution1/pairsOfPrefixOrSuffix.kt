package mocks.interview.pairsOfPrefixOrSuffix.solution1

/**
 * Given a list of words, find all the pair of words for which one of them is a prefix or suffix of the other.
 */

// val testCase = listOf("an", "ana", "anar", "anarchy", "robolectic", "robo", "ro", "row", "dim", "dima", "duma", "dumas", "xenial", "rock", "rockey", "chunk", "chadder", "adder", "der", "uma", "im", "o", "ow", "archy", "ar")

fun findPrefixOrSuffixPairs(words: List<String>): List<Pair<String, String>> {
    val trie = Trie()
    val reverseTrie = Trie()

    for (word in words) {
        trie.insert(word, true)
        reverseTrie.insert(word, false)
    }

    val wordPairs = mutableListOf<Pair<String, String>>()
    getNextWords(trie.root, wordPairs)
    getNextWords(reverseTrie.root, wordPairs)

    return wordPairs
}

fun getNextWords(node: TrieNode, wordPairs: MutableList<Pair<String, String>>): List<String> {
    val nextWords = mutableListOf<String>()

    val nextNonDelimiterChildren = node.children.filterKeys { it != DELIMITER }

    for ((_, child) in nextNonDelimiterChildren) {
        nextWords.addAll(getNextWords(child, wordPairs))
    }

    if (DELIMITER in node.children) {
        val wordEndingHere = node.children[DELIMITER]!!.word!!
        for (nextWord in nextWords) {
            wordPairs.add(wordEndingHere to nextWord)
        }
        nextWords.add(wordEndingHere)
    }

    return nextWords
}

const val DELIMITER = '*'

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
    var word: String? = null
}

class Trie {
    val root = TrieNode()

    fun insert(word: String, leftToRight: Boolean) {
        var currentNode = root

        val range = if (leftToRight) word.indices else word.indices.reversed()

        for (idx in range) {
            val ch = word[idx]

            if (ch !in currentNode.children) {
                currentNode.children[ch] = TrieNode()
            }

            currentNode = currentNode.children[ch]!!
        }

        currentNode.children[DELIMITER] = TrieNode().also {
            it.word = word
        }
    }
}