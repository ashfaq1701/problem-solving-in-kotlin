package ae.hard.multiStringSearch.solution2

fun multiStringSearch(bigString: String, smallStrings: List<String>): List<Boolean> {
    val suffixTrie = ModifiedSuffixTree(bigString)

    return smallStrings.map { smallString ->
        suffixTrie.contains(smallString)
    }
}

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
}

class ModifiedSuffixTree(val str: String) {
    val rootNode = TrieNode()

    init {
        populateSuffixTrie(str)
    }

    fun populateSuffixTrie(str: String) {
        for (i in str.indices) {
            insertSubstringStartingAt(str, i)
        }
    }

    fun insertSubstringStartingAt(str: String, idx: Int) {
        var currentNode = rootNode

        for (j in idx .. str.lastIndex) {
            val letter = str[j]

            if (!currentNode.children.containsKey(letter)) {
                currentNode.children[letter] = TrieNode()
            }

            currentNode = currentNode.children[letter]!!
        }
    }

    fun contains(str: String): Boolean {
        var currentNode = rootNode

        for (i in str.indices) {
            val letter = str[i]

            if (!currentNode.children.containsKey(letter)) {
                return false
            }

            currentNode = currentNode.children[letter]!!
        }

        return true
    }
}