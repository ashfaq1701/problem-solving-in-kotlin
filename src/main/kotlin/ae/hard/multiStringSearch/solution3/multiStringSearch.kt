package ae.hard.multiStringSearch.solution3

// O(ns + bs) Time | O(ns) Space
fun multiStringSearch(bigString: String, smallStrings: List<String>): List<Boolean> {
    val trie = Trie()

    smallStrings.forEach { smallString ->
        trie.insert(smallString)
    }

    val containsSet = mutableSetOf<String>()
    for (i in bigString.indices) {
        findSmallStringsIn(bigString, i, trie, containsSet)
    }

    return smallStrings.map { smallString ->
        containsSet.contains(smallString)
    }
}

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
    var word: String? = null
}

class Trie {
    val rootNode = TrieNode()
    val endSymbol = '*'

    fun insert(str: String) {
        var currentNode = rootNode

        for (i in str.indices) {
            val letter = str[i]

            if (!currentNode.children.containsKey(letter)) {
                currentNode.children[letter] = TrieNode()
            }

            currentNode = currentNode.children[letter]!!
        }

        currentNode.children[endSymbol] = TrieNode().also {
            it.word = str
        }
    }
}

fun findSmallStringsIn(bigString: String, startIdx: Int, trie: Trie, containsSet: MutableSet<String>) {
    var currentNode = trie.rootNode

    for (idx in startIdx .. bigString.lastIndex) {
        val letter = bigString[idx]

        if (!currentNode.children.containsKey(letter)) {
            break
        }
        currentNode = currentNode.children[letter]!!

        if (currentNode.children.containsKey(trie.endSymbol)) {
            containsSet.add(currentNode.children[trie.endSymbol]!!.word!!)
        }
    }
}