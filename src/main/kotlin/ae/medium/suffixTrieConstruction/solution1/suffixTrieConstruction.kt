package ae.medium.suffixTrieConstruction.solution1

data class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf<Char, TrieNode>()
)

class SuffixTrie(str: String) {
    val endSymbol = '*'
    var root = TrieNode()

    init { populate(str) }

    fun populate(str: String) {
        for (i in 0 .. str.lastIndex) {
            insertSubstringStartingAt(i, str)
        }
    }

    fun insertSubstringStartingAt(idx: Int, str: String) {
        var currentNode = root

        for (i in idx .. str.lastIndex) {
            if (!currentNode.children.containsKey(str[i])) {
                currentNode.children[str[i]] = TrieNode()
            }

            currentNode = currentNode.children[str[i]]!!
        }

        currentNode.children[endSymbol] = TrieNode()
    }

    fun contains(str: String): Boolean {
        var currentNode = root

        for (c in str) {
            if (!currentNode.children.containsKey(c)) {
                return false
            }

            currentNode = currentNode.children[c]!!
        }

        return currentNode.children.containsKey(endSymbol)
    }
}
