package ae.medium.groupAnagrams.solution1

fun groupAnagrams(words: List<String>): List<List<String>> {
    if (words.isEmpty()) return listOf()

    val sortedWords = words.map { word ->
        String(word.toCharArray().sortedArray())
    }

    val indices = MutableList(words.size) { it }
    indices.sortWith(Comparator<Int> { idx1, idx2 ->
        sortedWords[idx1].compareTo(sortedWords[idx2])
    })

    val result = mutableListOf<List<String>>()
    var currentAnagramGroup = mutableListOf<String>()
    var currentAnagram = sortedWords[indices[0]]

    for (idx in indices) {
        val word = words[idx]
        val sortedWord = sortedWords[idx]

        if (sortedWord == currentAnagram) {
            currentAnagramGroup.add(word)
        } else {
            result.add(currentAnagramGroup)
            currentAnagram = sortedWord
            currentAnagramGroup = mutableListOf(word)
        }
    }

    result.add(currentAnagramGroup)
    return result
}