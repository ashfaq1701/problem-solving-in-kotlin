package ctci.groupAnagrams.solution1

class Solution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val groups = mutableMapOf<String, MutableList<String>>()

        for (str in strs) {
            val sortedStr = String(str.toCharArray().apply { sort() })

            if (sortedStr !in groups) {
                groups[sortedStr] = mutableListOf<String>()
            }

            groups[sortedStr]!!.add(str)
        }

        return groups.values.map { it.toList() }
    }
}