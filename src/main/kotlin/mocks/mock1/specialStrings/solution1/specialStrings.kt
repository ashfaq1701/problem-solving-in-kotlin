package mocks.mock1.specialStrings.solution1

import kotlin.math.max

fun specialStrings(strings: List<String>): List<String> {
    val trie = SpecialTrie()

    for (string in strings) {
        trie.insert(string)
    }

    return strings.filter { countComposition(it, 0, trie) > 1 }
}

fun countComposition(word: String, start: Int, trie: SpecialTrie): Int {
    if (start == word.length) {
        return 0
    }

    var maxComposition = Int.MIN_VALUE
    var currentNode = trie.root

    for (idx in start .. word.lastIndex) {
        val ch = word[idx]

        if (currentNode.children.containsKey(ch)) {
            currentNode = currentNode.children[ch]!!
        } else {
            break
        }

        if (currentNode.children.containsKey(trie.delimiter)) {
            val nextCompositionCount = countComposition(word, idx + 1, trie)

            if (nextCompositionCount != Int.MIN_VALUE) {
                maxComposition = max(maxComposition, nextCompositionCount + 1)
            }
        }
    }

    return maxComposition
}

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
}

class SpecialTrie {
    val root = TrieNode()
    val delimiter = '*'

    fun insert(word: String) {
        var currentNode = root

        for (c in word) {
            if (!currentNode.children.containsKey(c)) {
                currentNode.children[c] = TrieNode()
            }

            currentNode = currentNode.children[c]!!
        }

        currentNode.children[delimiter] = TrieNode()
    }
}