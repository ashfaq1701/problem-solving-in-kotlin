package leetcode.repeatedDnaSequences.solution2

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
        val seen = mutableSetOf<Int>()

        val len = 10
        var hash = 0

        for (start in 0 .. s.length - len) {
            if (start == 0) {
                for (i in start until len) {
                    hash += (charCodes[s[i]]!! * 4.0.pow(len - i - 1)).toInt()
                }
            } else {
                hash = ((hash - charCodes[s[start - 1]]!! * 4.0.pow(len - 1)) * 4 + charCodes[s[start + len - 1]]!!).toInt()
            }

            if (seen.contains(hash)) {
                sequences.add(s.substring(start, start + len))
            }

            seen.add(hash)
        }

        return sequences.toList()
    }
}