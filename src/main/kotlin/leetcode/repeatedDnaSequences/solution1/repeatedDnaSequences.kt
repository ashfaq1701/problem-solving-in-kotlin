package leetcode.repeatedDnaSequences.solution1

import kotlin.math.pow

// Rabin Karp Algorithm
class Solution {
    fun findRepeatedDnaSequences(s: String): List<String> {
        val charCodes = mapOf(
            'A' to 1,
            'C' to 2,
            'G' to 3,
            'T' to 4
        )

        val sequences = mutableSetOf<String>()

        if (s.length < 10) return listOf()

        val seen = mutableSetOf<Int>()

        var hash = 0
        for (i in 0 until 10) {
            hash += (charCodes[s[i]]!! * 4.0.pow(10 - i - 1)).toInt()
        }

        seen.add(hash)

        for (i in 10 until s.length) {
            hash = ((hash - charCodes[s[i - 10]]!! * 4.0.pow(9)) * 4 + charCodes[s[i]]!!).toInt()

            if (seen.contains(hash)) {
                sequences.add(s.substring(i - 10 + 1, i + 1))
            }

            seen.add(hash)
        }

        return sequences.toList()
    }
}