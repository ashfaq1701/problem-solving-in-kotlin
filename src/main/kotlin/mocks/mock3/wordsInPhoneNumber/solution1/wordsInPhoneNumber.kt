package mocks.mock3.wordsInPhoneNumber.solution1

fun wordsInPhoneNumber(phoneNumber: String, words: List<String>): List<String> {
    val trie = SuffixTrie(phoneNumber)

    return words.filter { trie.contains(it) }
}

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
}

class SuffixTrie(phoneNumber: String) {
    val root = TrieNode()
    val keyMap = mapOf(
        1 to listOf(),
        2 to listOf('a', 'b', 'c'),
        3 to listOf('d', 'e', 'f'),
        4 to listOf('g', 'h', 'i'),
        5 to listOf('j', 'k', 'l'),
        6 to listOf('m', 'n', 'o'),
        7 to listOf('p', 'q', 'r', 's'),
        8 to listOf('t', 'u', 'v'),
        9 to listOf('w', 'x', 'y', 'z'),
        0 to listOf()
    )

    val reverseKeyMap: Map<Char, Char> by lazy {
        keyMap.flatMap { (digit, chars) ->
            chars.map { it to '0' + digit }
        }.toMap()
    }

    init {
        populateSuffixTrie(phoneNumber)
    }

    fun populateSuffixTrie(phoneNumber: String) {
        for (i in phoneNumber.indices) {
            insertSubstringStartingAt(phoneNumber, i)
        }
    }

    fun insertSubstringStartingAt(phoneNumber: String, startIdx: Int) {
        var currentNode = root

        for (i in startIdx until phoneNumber.length) {
            val digit = phoneNumber[i]

            if (!currentNode.children.containsKey(digit)) {
                currentNode.children[digit] = TrieNode()
            }

            currentNode = currentNode.children[digit]!!
        }
    }

    fun getDigit(char: Char): Char = reverseKeyMap[char]!!

    fun contains(word: String): Boolean {
        var currentNode = root

        for (char in word) {
            val digit = reverseKeyMap[char] ?: return false

            if (!currentNode.children.containsKey(digit)) {
                return false
            }

            currentNode = currentNode.children[digit]!!
        }

        return true
    }
}
