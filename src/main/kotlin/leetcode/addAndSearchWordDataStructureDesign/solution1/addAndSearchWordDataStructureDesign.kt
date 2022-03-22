package leetcode.addAndSearchWordDataStructureDesign.solution1

class WordDictionary() {

    private val trie = Trie()

    fun addWord(word: String) {
        trie.insert(word)
    }

    fun search(word: String): Boolean {
        return trie.contains(word)
    }
}

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
}

const val DELIMITER = '*'

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

        current.children[DELIMITER] = TrieNode()
    }

    infix fun contains(str: String): Boolean {
        return containsFromRootStartingAt(str, 0, root)
    }

    private fun containsFromRootStartingAt(str: String, idx: Int, currentNode: TrieNode): Boolean {
        if (idx == str.length) return DELIMITER in currentNode.children

        val ch = str[idx]

        if (ch == '.') {
            for ((_, nextNode) in currentNode.children) {
                if (containsFromRootStartingAt(str, idx + 1, nextNode)) {
                    return true
                }
            }
        } else if (ch in currentNode.children) {
            return containsFromRootStartingAt(str, idx + 1, currentNode.children[ch]!!)
        }

        return false
    }
}