package ae.medium.groupAnagrams.solution2

fun groupAnagrams(words: List<String>): List<List<String>> {

    val anagrams = mutableMapOf<String, MutableList<String>>()

    words.forEach { word ->
        val sortedWord = String(word.toCharArray().sortedArray())
        if (!anagrams.containsKey(sortedWord)) {
            anagrams[sortedWord] = mutableListOf(word)
        } else {
            anagrams[sortedWord]!!.add(word)
        }
    }

    return anagrams.values.toList()
}
