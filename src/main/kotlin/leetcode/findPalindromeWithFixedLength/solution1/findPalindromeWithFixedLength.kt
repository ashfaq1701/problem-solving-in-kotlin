package leetcode.findPalindromeWithFixedLength.solution1

class Solution {
    fun kthPalindrome(queries: IntArray, intLength: Int): LongArray {
        val maxLength = queries.maxOrNull()!!
        val items = palindromesOfLen(intLength, maxLength, intLength)
        return queries.map { query ->
            if (query <= items.size) {
                items[query - 1]
            } else {
                "-1"
            }
        }.map { it.toLong() }
            .toLongArray()
    }

    fun palindromesOfLen(len: Int, maxLength: Int, intLength: Int): List<String> {
        if (len == 1) {
            var result = ('1' .. '9').map { "$it" }
            if (len < intLength) {
                result = listOf("0").plus(result)
            }
            return result
        }

        if (len == 2) {
            var result = ('1' .. '9').map { "${it}${it}" }
            if (len < intLength) {
                result = listOf("00").plus(result)
            }
            return result
        }

        val newLevel = mutableListOf<String>()
        val lastLevel = palindromesOfLen(len - 2, maxLength, intLength)

        if (len < intLength) {
            for (item in lastLevel) {
                if (newLevel.size == maxLength) break
                newLevel.add("0${item}0")
            }
        }

        for (digit in '1' .. '9') {
            if (newLevel.size == maxLength) break

            for (item in lastLevel) {
                if (newLevel.size == maxLength) break

                newLevel.add("${digit}${item}${digit}")
            }
        }

        return newLevel
    }
}

fun main() {
    println(Solution().kthPalindrome(intArrayOf(1,2,3,4,5,90), 3))
}