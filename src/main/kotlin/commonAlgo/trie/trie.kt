package commonAlgo.trie

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