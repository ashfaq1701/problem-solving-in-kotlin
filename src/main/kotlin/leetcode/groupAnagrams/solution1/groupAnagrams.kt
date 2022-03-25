package leetcode.groupAnagrams.solution1

class Solution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val wordGroups = mutableMapOf<String, MutableList<String>>()

        for (word in strs) {
            val sortedWord = sortedStr(word)
            if (sortedWord !in wordGroups) {
                wordGroups[sortedWord] = mutableListOf()
            }
            wordGroups[sortedWord]!!.add(word)
        }

        return wordGroups.values.toList()
    }

    private fun sortedStr(str: String) = String(str.toCharArray().sorted().toCharArray())
}