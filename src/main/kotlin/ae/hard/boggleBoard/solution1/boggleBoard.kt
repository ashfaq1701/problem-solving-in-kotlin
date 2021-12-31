package ae.hard.boggleBoard.solution1

const val DELIMITER = '*'

fun boggleBoard(board: List<List<Char>>, words: List<String>): List<String> {
    val root = populateSuffixTrie(words)

    val result = mutableSetOf<String>()
    val visited = MutableList(board.size) { MutableList(board[0].size) { false } }

    for (r in board.indices) {
        for (c in board[0].indices) {
            dfsVisitNode(r, c, root, board, visited, result)
        }
    }

    return result.toList()
}

class TrieNode {
    val children: MutableMap<Char, TrieNode> = mutableMapOf()
    var word: String = ""
}

fun populateSuffixTrie(words: List<String>): TrieNode {
    val root = TrieNode()

    for (word in words) {
        var currentNode = root

        for (c in word) {
            if (!currentNode.children.containsKey(c)) {
                currentNode.children[c] = TrieNode()
            }

            currentNode = currentNode.children[c]!!
        }

        val delimiterNode = TrieNode().apply {
            this.word = word
        }
        currentNode.children[DELIMITER] = delimiterNode
    }

    return root
}

fun dfsVisitNode(r: Int, c: Int, currentNode: TrieNode, board: List<List<Char>>, visited: MutableList<MutableList<Boolean>>, result: MutableSet<String>) {
    if (visited[r][c]) return

    val letter = board[r][c]

    if (!currentNode.children.containsKey(letter)) return

    visited[r][c] = true

    val nextNode = currentNode.children[letter]!!

    if (nextNode.children.containsKey(DELIMITER)) {
        result.add(nextNode.children[DELIMITER]!!.word)
    }

    val neighbors = getNeighbors(r, c, board)

    for ((neighborRow, neighborCol) in neighbors) {
        dfsVisitNode(neighborRow, neighborCol, nextNode, board, visited, result)
    }

    visited[r][c] = false
}

fun getNeighbors(r: Int, c: Int, board: List<List<Char>>): List<Pair<Int, Int>> {
    val rows = board.size
    val cols = board[0].size

    return listOf(
        r - 1 to c + 1 ,
        r to c + 1,
        r + 1 to c + 1,
        r + 1 to c,
        r + 1 to c - 1,
        r to c - 1,
        r - 1 to c - 1,
        r - 1 to c
    ).filter { (row, col) ->
        row in 0 until rows && col in 0 until cols
    }
}
