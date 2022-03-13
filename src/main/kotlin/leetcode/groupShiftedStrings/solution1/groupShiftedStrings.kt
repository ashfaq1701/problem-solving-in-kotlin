package leetcode.groupShiftedStrings.solution1

class Solution {
    fun getDiff(ch1: Char, ch2: Char): Int {
        return (ch2 - ch1 + 26) % 26
    }

    fun getHash(str: String): String {
        var hash = mutableListOf<Int>()

        for (i in 1 until str.length) {
            hash.add(getDiff(str[i], str[i - 1]))
        }

        return hash.joinToString(",")
    }

    fun groupStrings(strings: Array<String>): List<List<String>> {
        val stringGroups = mutableMapOf<String, MutableList<String>>()

        for (str in strings) {
            val hash = getHash(str)

            if (hash !in stringGroups) {
                stringGroups[hash] = mutableListOf()
            }

            stringGroups[hash]!!.add(str)
        }

        return stringGroups.values.map { it.toList() }
    }
}